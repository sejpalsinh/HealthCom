package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HDoctors extends AppCompatActivity {
    ArrayList<String> dId = new ArrayList<>();
    ArrayList<String> dName = new ArrayList<>();
    ArrayList<String> qualifications = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdoctors);

        dId.add("101");
        dName.add("Ramesh");
        qualifications.add("MBBS");
        phone.add("8546213977");
        email.add("ramesh@hospital.com");
        dId.add("101");
        dName.add("Ramesh");
        qualifications.add("MBBS");
        phone.add("8546213977");
        email.add("ramesh@hospital.com");

        RecyclerView recyclerView = findViewById(R.id.viewDoctors);
        DoctorsAdapter doctorsAdapter = new DoctorsAdapter(this, dId, dName, qualifications, phone, email);
        recyclerView.setAdapter(doctorsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
