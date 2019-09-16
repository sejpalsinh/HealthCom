package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    //An ArrayList for Spinner Items
    private ArrayList<String> states;
    private ArrayList<String> arrayDistricts;
    private String[] strFacilities;
    //JSON Array
    private JSONArray result;
    private List<String> lstFacilities;
    private ArrayList<Integer> lstNFacilities;
    private JSONObject jsonFacilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__home);

        //Initializing the ArrayList
        states = new ArrayList<String>();
        arrayDistricts = new ArrayList<String>();
        hsptType = findViewById(R.id.hpstType);
        selectedHsptType = findViewById(R.id.govt);
        srchHospitals = findViewById(R.id.getHospitals);
        srchHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnState.getSelectedItemPosition() == 0){
                    Toast.makeText(Patient_Home.this, "Please Select State and District", Toast.LENGTH_SHORT).show();
                } else if(hsptType.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Patient_Home.this, "Please select Hospital Type", Toast.LENGTH_SHORT).show();
                } else if(multiSelectionSpinner.getSelectedIndicies().isEmpty()){
                    Toast.makeText(Patient_Home.this, "Please select atleast one facility", Toast.LENGTH_SHORT).show();
                } else{
                    List<Integer> indicesSelected = multiSelectionSpinner.getSelectedIndicies();
                    strFacilities = new String[indicesSelected.size()];
                    try {
                        JSONArray jsonArray = jsonFacilities.getJSONArray("btnFaci");

                        for(int x = 0; x < indicesSelected.size(); x++){
                            JSONObject jsonObject = jsonArray.getJSONObject(indicesSelected.get(x));
                            String s = jsonObject.getString("f_id");
                            strFacilities[x] = s;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), RecyclerViewList.class);
                    intent.putExtra("state", spnState.getSelectedItem().toString());
                    intent.putExtra("district", spnDistrict.getSelectedItem().toString());

                    if(selectedHsptType.isChecked()){
                        intent.putExtra("type", "govt");
                    } else {
                        intent.putExtra("type", "self");
                    }
                    intent.putExtra("btnFaci", strFacilities);

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
                    for (int j=0; j < districts.length(); j++){

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
        getFacilities();
    }

    private void getFacilities() {
        try{
            jsonFacilities = new JSONObject(OpenJSON.readJSONFromAsset(Patient_Home.this, "facilities.json"));
            JSONArray facilities = jsonFacilities.getJSONArray("facilities");
            for (int i=0; i < facilities.length(); i++){
                String facility = facilities.getJSONObject(i).getString("f_name");
                lstFacilities.add(facility);
            }
            multiSelectionSpinner.setItems(lstFacilities);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    private void getStates(JSONArray j){
        for(int i=0;i<j.length();i++){
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

}

