package com.example.healthcom;
import  androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText uname,pass;
    BackgroundTask bt;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.edt_name);
        pass = findViewById(R.id.edt_pass);
        preferences = getSharedPreferences("HealthCom", MODE_PRIVATE);
        editor = preferences.edit();

        if(!preferences.getString("uid", "").equals(""))
        {
            String fg = preferences.getString("flag","not");
            if(fg.equals("1"))
            {
                startActivity(new Intent(getApplicationContext(), Patient_Home.class));
                finish();
            }
            if(fg.equals("2"))
            {
                startActivity(new Intent(getApplicationContext(), Hospital_Home.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void goRegister(View view) {   //for registration if user not exist .....
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register Your self As");
        builder.setMessage("");

        builder.setPositiveButton("Patient", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(),Registration_patient.class));   //open web view for register patient
            }
        });
        builder.setNeutralButton("Hospital",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(),Registration_hospital.class));  //open web view for register Hospital
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void checkUserLogin(View view) {
        if(!(Patterns.EMAIL_ADDRESS.matcher(uname.getText().toString()).matches() && !pass.getText().toString().equals("")))
        {
            Toast.makeText(this, "Check User Name and Password it's not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        bt = new BackgroundTask(new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                System.out.println("xxxxxxxxxoot "+output);
                String[] st = output.split(" ");
                output = st[0];
                System.out.println("foocoo: "+st);
                if(output.equals("1") ) {
                    editor.putString("uid", st[1]);
                    editor.putString("flag",st[0]);
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(), Patient_Home.class));
                    finish();
                    return;
                }
                if(output.equals("2")) {
                    editor.putString("uid", st[1]);
                    editor.putString("flag",st[0]);
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(), Hospital_Home.class));
                    finish();
                    return;
                }
                if(output.equals("0")) {
                    Toast.makeText(Login.this, "Username or Password note Valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Login.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                bt=null;
            }
        });
        bt.execute("login",uname.getText().toString(),pass.getText().toString());

    }
}
