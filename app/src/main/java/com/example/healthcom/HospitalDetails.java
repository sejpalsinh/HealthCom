package com.example.healthcom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HospitalDetails extends AppCompatActivity {
    Button btnDocs, btnFaci, btnLoc, btnAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        btnDocs = findViewById(R.id.btnDoctors);
        btnFaci = findViewById(R.id.btnFacilities);
        btnLoc = findViewById(R.id.btnLocation);
        btnAppoint = findViewById(R.id.btnAppoint);

        Intent intent = getIntent();
        int id = intent.getIntExtra("h_id", 0);

        btnFaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HospitalDetails.this, HFacilities.class));
            }
        });

        btnDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HospitalDetails.this, HDoctors.class));
            }
        });

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
