package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Patient_Home extends AppCompatActivity {

    //Declaring an Spinner
    private Spinner spnState, spnDistrict;
    MultiSelectionSpinner multiSelectionSpinner;
    private RadioGroup hsptType;
    private RadioButton selectedHsptType;
    private Button srchHospitals;
    BackgroundTask bt;

    //An ArrayList for Spinner Items
    private ArrayList<String> states;
    private ArrayList<String> arrayDistricts;
    private String strFacilities;
    //JSON Array
    private JSONArray result;
    private List<String> lstFacilities;
    private ArrayList<Integer> lstNFacilities;
    private JSONObject jsonFacilities;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private ProgressDialog pd = null;
    int co = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__home);
        preferences = getSharedPreferences("HealthCom",MODE_PRIVATE);
        editor = preferences.edit();
        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                if(output.equals("sejpal") && co == 0 )
                {
                    bt.execute("getfacility");
                    co = 1;
                }
                co = 0;
                getFacilities(output);
                if (Patient_Home.this.pd != null) {
                    Patient_Home.this.pd.dismiss();
                }
                bt=null;
            }
        });
        this.pd = ProgressDialog.show(this, "Fancy App",
                "Loading...Please wait...", true, false);
        bt.execute("getfacility");

        //Initializing the ArrayList
        states = new ArrayList<String>();
        arrayDistricts = new ArrayList<String>();
        hsptType = findViewById(R.id.hpstType);
        selectedHsptType = findViewById(R.id.govt);
        srchHospitals = findViewById(R.id.getHospitals);
        srchHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnState.getSelectedItemPosition() == 0) {
                    Toast.makeText(Patient_Home.this, "Please Select State and District", Toast.LENGTH_SHORT).show();
                } else if (hsptType.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Patient_Home.this, "Please select Hospital Type", Toast.LENGTH_SHORT).show();
                } else if (multiSelectionSpinner.getSelectedIndicies().isEmpty()) {
                    Toast.makeText(Patient_Home.this, "Please select atleast one facility", Toast.LENGTH_SHORT).show();
                } else {
                    List<Integer> indicesSelected = multiSelectionSpinner.getSelectedIndicies();
                    strFacilities = "";
                    try {
                        JSONArray jsonArray = jsonFacilities.getJSONArray("facilities");

                        for (int x = 0; x < indicesSelected.size(); x++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(indicesSelected.get(x));
                            String s = jsonObject.getString("f_id");
                            strFacilities = strFacilities + s+ ",";
                        }
                        strFacilities = strFacilities.substring(0,strFacilities.length()-1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("ererer "+e);
                    }
                    Intent intent = new Intent(getApplicationContext(), RecyclerViewList.class);
                    String type = "";
                    if (selectedHsptType.isChecked()) {
                        intent.putExtra("type", "govt");
                        type = "Gov";
                    } else {
                        intent.putExtra("type", "self");
                        type = "Self";
                    }
                    strFacilities = "("+strFacilities+")";
                    intent.putExtra("btnFaci",strFacilities );

                    bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            System.out.println("ppppppppppppppppppppppp :"+output);
                            bt = null;
                        }
                    });
                    String st = "\""+spnState.getSelectedItem().toString()+"\"";
                    String ct = "\""+spnDistrict.getSelectedItem().toString()+"\"";
                    //strFacilities = "("+strFacilities+")";
                    bt.execute("getSelectedHospita",spnState.getSelectedItem().toString(),spnDistrict.getSelectedItem().toString(),type,strFacilities);
                    String sq = "SELECT DISTINCT(h.h_id), h.h_name FROM datagov_hospital h INNER JOIN datagov_facility_hospital hf ON h.h_id = hf.f_h_id WHERE h.h_state LIKE "+st+" AND h.h_city LIKE "+ct+" AND hf.f_id IN "+strFacilities;
                    intent.putExtra("sqlstring", sq);
                    System.out.println("dtdtdt :"+sq);
                    startActivity(intent);
                }
            }
        });

        //Initializing Spinner
        spnState = (Spinner) findViewById(R.id.spinner);
        spnDistrict = (Spinner) findViewById(R.id.spinner2);
        spnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    JSONObject jDistricts = result.getJSONObject(i);
                    System.out.println(jDistricts);
                    JSONArray districts = jDistricts.getJSONArray("districts");
                    arrayDistricts.clear();
                    for (int j = 0; j < districts.length(); j++) {

                        arrayDistricts.add(districts.getString(j));
                    }
                    spnDistrict.setAdapter(new ArrayAdapter<String>(Patient_Home.this, android.R.layout.simple_spinner_dropdown_item, arrayDistricts));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        try {
            JSONObject objStates = new JSONObject(OpenJSON.readJSONFromAsset(this, "states-and-districts.json"));
            result = objStates.getJSONArray("states");
            getStates(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        multiSelectionSpinner = findViewById(R.id.multiSelectionSpinner);
        lstFacilities = new ArrayList<String>();
        lstNFacilities = new ArrayList<Integer>();
    }

    private void getFacilities(String jstr) {
        try {
            //jsonFacilities = new JSONObject(OpenJSON.readJSONFromAsset(Patient_Home.this, "facilities.json"));
            jsonFacilities = new JSONObject(jstr);
            JSONArray facilities = jsonFacilities.getJSONArray("facilities");
            for (int i = 0; i < facilities.length(); i++) {
                String facility = facilities.getJSONObject(i).getString("f_name");
                lstFacilities.add(facility);
            }
            multiSelectionSpinner.setItems(lstFacilities);
        } catch (Exception e) {
            System.out.println("erererererererer:");
        }
    }


    private void getStates(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                states.add(json.getString("state"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spnState
        spnState.setAdapter(new ArrayAdapter<String>(Patient_Home.this, android.R.layout.simple_spinner_dropdown_item, states));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void log_out(MenuItem item) {
        editor.putString("uid","");
        editor.putString("flag","");
        editor.commit();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void abt_Us(MenuItem item) {
    }




}

