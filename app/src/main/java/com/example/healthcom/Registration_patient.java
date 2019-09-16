package com.example.healthcom;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Registration_patient extends AppCompatActivity {

    EditText name,bdate,uname,pass,pass2,address,number;
    RadioButton male,female;
    BackgroundTask bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_patient);
        name = findViewById(R.id.edt_name);
        number = findViewById(R.id.edt_mob);
        address = findViewById(R.id.edt_address);
        bdate = findViewById(R.id.edt_bdate);
        uname = findViewById(R.id.edt_uname);
        pass = findViewById(R.id.edt_pass);
        pass2 = findViewById(R.id.edt_pass2);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
    }

    public void clearAll(View view) {
        name.setText("");
        number.setText("");
        address.setText("");
        bdate.setText("");
        uname.setText("");
        pass.setText("");
        pass2.setText("");
        male.setChecked(false);
        female.setChecked(false);
    }

    public void registorPatient(View view) {
        if( !name.getText().toString().equals("")
                && !number.getText().toString().equals("")
                && !address.getText().toString().equals("")
                && !bdate.getText().toString().equals("")
                && !uname.getText().toString().equals("")
                && !pass.getText().toString().equals("")
                && !pass2.getText().toString().equals("")
                && (male.isChecked() ||female.isChecked()))

        {
            if(!Patterns.EMAIL_ADDRESS.matcher(uname.getText().toString()).matches())
            {
                Toast.makeText(this, "User name must be Email address", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!pass.getText().toString().equals(pass2.getText().toString()))
            {
                Toast.makeText(this, "Password Dose not Match", Toast.LENGTH_SHORT).show();
                return;
            }

            String p_name,p_address,p_bdate,p_number,p_gen,p_uname,p_password,p_flag;
            p_name = name.getText().toString();
            p_address = address.getText().toString();
            p_bdate = bdate.getText().toString();
            p_number = number.getText().toString();
            p_gen = male.isChecked() ? "male" : "female";
            p_uname = uname.getText().toString();
            p_password = pass.getText().toString();
            p_flag = "1";
            bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                   // System.out.println("xxxxxxxxxoot "+output);
                    bt=null;
                }
            });
            //System.out.println("registorrr"+);
            bt.execute("regi2",p_name,p_address,p_bdate,p_number,p_gen,p_uname,p_password,p_flag);
        }
        else
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
