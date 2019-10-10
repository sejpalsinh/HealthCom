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
import android.widget.EditText;
import android.widget.Toast;

public class hospital_addDoctor extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String uid;
    EditText name,number,email,time,post;
    BackgroundTask bt;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_add_doctor);
        preferences = getSharedPreferences("HealthCom", MODE_PRIVATE);
        editor = preferences.edit();
        uid = preferences.getString("uid", "tony");
        name = findViewById(R.id.et_name);
        number = findViewById(R.id.et_doc_number);
        email = findViewById(R.id.et_mailid);
        time = findViewById(R.id.et_time);
        post = findViewById(R.id.ed_post);
    }


    public void clear_all(View view) {
        name.setText("");
        number.setText("");
        email.setText("");
        time.setText("");
        post.setText("");
    }

    public void addDoc(View view) {
        String n = "\""+name.getText().toString()+"\"";
        String num = "\""+number.getText().toString()+"\"";
        String e = "\""+email.getText().toString()+"\"";
        String p = "\""+post.getText().toString()+"\"";
        String t = "\""+time.getText().toString()+"\"";
        String sqlstr = "INSERT INTO `datagov_doctor`(`d_h_id`, `d_name`, `d_number`, `d_email`, `d_post`, `d_time`) VALUES ("+uid+","+n+","+num+","+e+","+p+","+t+")";
        System.out.println("sqlsqlsql : "+sqlstr);
        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                if(output.equals("sejpal"))
                {
                    Toast.makeText(hospital_addDoctor.this, "Not Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(hospital_addDoctor.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                }
                bt=null;
            }
        });
        bt.execute("addDataDocFc",sqlstr);

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
