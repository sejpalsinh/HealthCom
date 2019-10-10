package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class hospital_addFacilitie extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String uid;
    LinearLayout linearLayout1;
    BackgroundTask bt;
    private ArrayList<String> lstFacilities;
    private JSONObject jsonFacilities;
    private ProgressDialog pd = null;
    int co = 0;
    Spinner spinner;
    int [] getFId;
    EditText etcost,fname;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_add_facilitie);
        preferences = getSharedPreferences("HealthCom", MODE_PRIVATE);
        editor = preferences.edit();
        uid = preferences.getString("uid","sejpal");
        linearLayout1 = findViewById(R.id.lyo1);
        spinner = findViewById(R.id.spr_fac);
        lstFacilities = new ArrayList<String>();
        etcost = findViewById(R.id.et_cos);
        fname = findViewById(R.id.ed_fcname);


        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                if(output.equals("sejpal") && co == 0 )
                {
                    bt.execute("getfacility");
                    co = 1;
                    if(output.equals("sejpal"))
                    {
                        Toast.makeText(hospital_addFacilitie.this, "Please Check Yout Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
                co = 0;
                getFacilities(output);
                if (hospital_addFacilitie.this.pd != null)
                {
                    hospital_addFacilitie.this.pd.dismiss();
                }
                bt=null;
            }
        });
        this.pd = ProgressDialog.show(this, "Fancy App",
                "Loading...Please wait...", true, false);
        bt.execute("getfacility");


    }

    private void getFacilities(String jstr) {
        try {
            jsonFacilities = new JSONObject(jstr);
            JSONArray facilities = jsonFacilities.getJSONArray("facilities");
            getFId = new int[facilities.length()];
            for (int i = 0; i < facilities.length(); i++) {
                String facility = facilities.getJSONObject(i).getString("f_name");
                getFId[i] = facilities.getJSONObject(i).getInt("f_id");
                lstFacilities.add(facility);
            }
            spinner.setAdapter(new ArrayAdapter<String>(hospital_addFacilitie.this, android.R.layout.simple_spinner_dropdown_item, lstFacilities));
        } catch (Exception e) {
            System.out.println("erererererererer:");
        }
    }


    //===============logout
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

    public void add_only_f(View view) {
        linearLayout1.setVisibility(View.VISIBLE);
    }

    public void closo_show(View view) {
        linearLayout1.setVisibility(View.GONE);
    }

    public void add_f_c(View view) {
        int fid = getFId[(int) spinner.getSelectedItemId()];
        String co = etcost.getText().toString();
        String sqlstr = "INSERT INTO `datagov_facility_hospital`( `f_h_id`, `f_id`, `f_cost`) VALUES ("+uid+","+fid+","+co+")";
        System.out.println("sqlsqlsql : "+sqlstr);
        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                if(output.equals("sejpal"))
                {
                    Toast.makeText(hospital_addFacilitie.this, "Not Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(hospital_addFacilitie.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                }
                bt=null;
            }
        });
        bt.execute("addDataDocFc",sqlstr);
        //INSERT INTO `datagov_facility`(`f_name`) VALUES ("lag check")
    }

    public void add_f_name(View view) {

        String co = fname.getText().toString();
        String sqlstr = "INSERT INTO `datagov_facility`(`f_name`) VALUES (\""+co+"\")";
        System.out.println("sqlsqlsql : "+sqlstr);
        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                if(output.equals("sejpal"))
                {
                    Toast.makeText(hospital_addFacilitie.this, "Not Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(hospital_addFacilitie.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                }
                bt=null;
            }
        });
        bt.execute("addDataDocFc",sqlstr);
        //
    }
}
