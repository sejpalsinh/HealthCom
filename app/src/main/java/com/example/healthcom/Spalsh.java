package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Spalsh extends AppCompatActivity {

    BackgroundTask bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);


        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("ppppppppppppppppppppppp :"+output);
                bt = null;
            }
        });
        bt.execute("getfacility");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        },4000);

    }
}
