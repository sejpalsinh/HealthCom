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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class hospital_addFacilitie extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String uid;
    LinearLayout linearLayout1;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_add_facilitie);
        preferences = getSharedPreferences("HealthCom", MODE_PRIVATE);
        editor = preferences.edit();
        uid = preferences.getString("uid ","sejpal");
        linearLayout1 = findViewById(R.id.lyo1);
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
}
