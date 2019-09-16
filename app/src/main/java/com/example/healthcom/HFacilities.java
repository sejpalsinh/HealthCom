package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HFacilities extends AppCompatActivity {
    private ArrayList<String> hFacilities = new ArrayList<>();
    private ArrayList<Integer> hCost = new ArrayList<>();
    JSONArray jListFaci;
    JSONObject jOneFaci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hfacilities);

//        for(int i = 0; i < jListFaci.length(); i++){
//            try {
//                jOneFaci = jListFaci.getJSONObject(i);
//
//                hFacilities.add(jOneFaci.getString("f_name"));
//                hCost.add(jOneFaci.getInt("f_id"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        hFacilities.add("Physiotherapy");
        hCost.add(3000);
        hFacilities.add("Physiotherapy");
        hCost.add(3000);
        hFacilities.add("Physiotherapy");
        hCost.add(3000);
        RecyclerView recyclerView = findViewById(R.id.viewFacilities);
        FacilitiesAdapter facilitiesAdapter = new FacilitiesAdapter(this, hFacilities, hCost);
        recyclerView.setAdapter(facilitiesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}