package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;

public class Hospital_Home extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList itemsArrayList;
    Doctor_List adapter;
    ListView itemsListView;
    String uid;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__home);
        preferences = getSharedPreferences("HealthCom",MODE_PRIVATE);
        editor = preferences.edit();
        uid = preferences.getString("uid ","sejpal");

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

    public void show_Doctor(View view) {
        startActivity(new Intent(getApplicationContext(),hospital_showDoctor.class));
    }

    public void show_Facilities(View view) {
        startActivity(new Intent(getApplicationContext(),hospital_showFacilities.class));
    }

    public void add_Doc(View view) {
        startActivity(new Intent(getApplicationContext(),hospital_addDoctor.class));
    }

    public void add_Fac(View view) {
        startActivity(new Intent(getApplicationContext(),hospital_addFacilitie.class));
    }

    public void search_Pa(View view) {
        startActivity(new Intent(getApplicationContext(),hospital_searchPatient.class));
    }
}
