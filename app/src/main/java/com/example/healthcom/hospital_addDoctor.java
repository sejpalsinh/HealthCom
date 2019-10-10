package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class hospital_addDoctor extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_add_doctor);
        preferences = getSharedPreferences("HealthCom", MODE_PRIVATE);
        editor = preferences.edit();
        uid = preferences.getString("uid ","sejpal");

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
}
