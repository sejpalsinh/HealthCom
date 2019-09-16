package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Registration_hospital extends AppCompatActivity {

    EditText edt_hname,edt_haddress,edt_hcity,edt_htime,edt_hnumber,edt_user,edt_pass,edt_pass2;
    RadioGroup bg_htype;
    RadioButton rb_gov,rb_self;
    BackgroundTask bt;
    Spinner sp_state,sp_city;

    private ArrayList<String> states;
    private ArrayList<String> arrayDistricts;

    private JSONArray result;
    JSONObject j = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_hospital);
        edt_hname = findViewById(R.id.edt_hosname);
        edt_haddress = findViewById(R.id.edt_address);
        sp_city = findViewById(R.id.spr_city);
        sp_state = findViewById(R.id.spr_state);
        edt_htime = findViewById(R.id.edt_htime);
        edt_hnumber = findViewById(R.id.edt_number);
        edt_user = findViewById(R.id.edt_uname);
        edt_pass = findViewById(R.id.edt_pass);
        edt_pass2 = findViewById(R.id.edt_pass2);
        bg_htype = findViewById(R.id.bg_hostype);
        rb_gov = findViewById(R.id.radioButton);
        rb_self = findViewById(R.id.radioButton2);
        states = new ArrayList<String>();
        arrayDistricts = new ArrayList<String>();

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    sp_city.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayDistricts));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //open the file
        try {
            JSONObject objStates = new JSONObject(OpenJSON.readJSONFromAsset(this, "states-and-districts.json"));
            result = objStates.getJSONArray("states");
            getStates(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //This method will fetch the data from the URL



    }

    public void btn_submit(View view) {
        if( !edt_hname.getText().toString().equals("")
                && !edt_haddress.getText().toString().equals("")
                && !(sp_state.isSelected() && sp_city.isSelected())
                && !edt_htime.getText().toString().equals("")
                && !edt_hnumber.getText().toString().equals("")
                && !edt_pass.getText().toString().equals("")
                && !edt_pass2.getText().toString().equals("")
                && (rb_gov.isChecked() ||rb_self.isChecked()))

        {
            if(!Patterns.EMAIL_ADDRESS.matcher(edt_user.getText().toString()).matches())
            {
                Toast.makeText(this, "User name must be Email address", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!edt_pass.getText().toString().equals(edt_pass2.getText().toString()))
            {
                Toast.makeText(this, "Password Dose not Match", Toast.LENGTH_SHORT).show();
                return;
            }

            String h_name,h_address,h_city,h_state,h_number,h_time,h_type,h_uname,h_password,h_flag;
            h_name = edt_hname.getText().toString();
            h_address = edt_haddress.getText().toString();
            h_city = sp_city.getSelectedItem().toString();
            h_state =  sp_state.getSelectedItem().toString();
            h_number = edt_hnumber.getText().toString();
            h_time = edt_htime.getText().toString();
            int i = bg_htype.getCheckedRadioButtonId();
            rb_self = findViewById(i);
            h_type = rb_self.getText().toString();
            h_uname = edt_user.getText().toString();
            h_password = edt_pass.getText().toString();
            h_flag = "2";
            bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                  //  System.out.println("xxxxxxxxxoot "+output);
                    bt=null;
                }
            });
            //System.out.println("registorrr"+);
            bt.execute("regi",h_name,h_address,h_city,h_state,h_number,h_time,h_type,h_uname,h_password,h_flag);
        }
        else
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void btn_cancle(View view) {   // to clear all the fields
        edt_hname.setText("");
        edt_haddress.setText("");
        sp_state.setSelected(false);
        sp_city.setSelected(false);
        edt_htime.setText("");
        edt_hnumber.setText("");
        edt_user.setText("");
        edt_pass.setText("");
        edt_pass2.setText("");
        rb_self.setChecked(false);
        rb_gov.setChecked(false);
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
        sp_state.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, states));
    }
}
