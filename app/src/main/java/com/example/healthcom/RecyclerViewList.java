package com.example.healthcom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.Buffer;
import java.util.ArrayList;

public class RecyclerViewList extends AppCompatActivity implements ListWithIDAdapter.OnHsptListener {
    private ArrayList<Integer> jHsptIDs = new ArrayList<>();
    private ArrayList<String> jHsptNames = new ArrayList<>();
    private ListWithIDAdapter adapter;
    BackgroundTask bt;
    JSONObject s_hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);

        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) throws JSONException {
                System.out.println("ppppppppppppppppppppppp :"+output);
                s_hospital = new JSONObject(output);
                System.out.println("Json obobob"+s_hospital.toString());
                bt = null;
            }
        });
        bt.execute("getSelectedHospita",getIntent().getStringExtra("sqlstring"));

        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");
        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");
        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");
        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");
        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");
        jHsptIDs.add(101);
        jHsptNames.add("Civil Hospital");
        jHsptIDs.add(110);
        jHsptNames.add("Sterling Hospital");
        jHsptIDs.add(204);
        jHsptNames.add("Govertment Hospital");

        RecyclerView recyclerView = findViewById(R.id.showHospitals);
        adapter = new ListWithIDAdapter(this, jHsptIDs, jHsptNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


           }

    @Override
    public void OnHsptClickListener(int position) {
        int i = adapter.getClickedHsptID(position);
        Intent intent = new Intent(this, HospitalDetails.class);
        intent.putExtra("h_id", i);
        startActivity(intent);
    }
}
