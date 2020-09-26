package com.squadron.craftcreatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText a_name,a_email,a_phonno,a_pass,a_cpass;
    Button sign_up;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createDatabase();

        sign_up = (Button)findViewById(R.id.sign_up_button);
        a_name = (EditText)findViewById(R.id.register_input_name);
        a_email = (EditText)findViewById(R.id.register_input_email);
        a_phonno = (EditText)findViewById(R.id.register_input_phoneno);
        a_pass = (EditText)findViewById(R.id.register_input_pass);
        a_cpass = (EditText)findViewById(R.id.register_input_cpass);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertIntoDb();
            }
        });
    }
    protected void createDatabase(){
        sqLiteDatabase = openOrCreateDatabase("craftcreatures", Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RegisterAdmin( AdminName VARCHAR PRIMARY KEY NOT NULL, AdminEmail VARCHAR, AdminPhoneNo VARCHAR, AdminPassword VARCHAR, AdminConfirmPassword VARCHAR);");
    }

    protected void InsertIntoDb(){
        String AdminName = a_name.getText().toString().trim();
        String AdminEmail = a_email.getText().toString().trim();
        String AdminPhoneNo = a_phonno.getText().toString().trim();
        String AdminPassword = a_pass.getText().toString().trim();
        String AdminConfirmPassword = a_cpass.getText().toString().trim();

        if (AdminName.equals("")||AdminEmail.equals("")||AdminPhoneNo.equals("")||AdminPassword.equals("")||AdminConfirmPassword.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            sqLiteDatabase.execSQL("INSERT INTO RegisterAdmin values('"+AdminName+"','"+AdminEmail+"','"+AdminPhoneNo+"','"+AdminPassword+"','"+AdminConfirmPassword+"');");
            Toast.makeText(getApplicationContext(), "Data Registerd", Toast.LENGTH_SHORT).show();
        }
    }

}