package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


import java.util.ArrayList;

public class Hospital_Home extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList itemsArrayList;
    Doctor_List adapter;
    ListView itemsListView;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__home);
        preferences = getSharedPreferences("HealthCom",MODE_PRIVATE);
        editor = preferences.edit();
        itemsListView  = (ListView) findViewById(R.id.list_view_items);
        //tv.setText(preferences.getString("uid ","sejpal"));
        Item item = new Item("sejpalsinh","tony");
        itemsArrayList = new ArrayList();
        adapter = new Doctor_List(this,itemsArrayList);
        System.out.println("joooooooooooooooooooooooooooooooooooooooooo");
        itemsArrayList.add(item);
        System.out.println("zoooooooooooooooooooooooooooo: "+adapter.getCount());
        itemsListView.setAdapter(adapter);
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
