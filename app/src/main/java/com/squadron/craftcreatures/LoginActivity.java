package com.squadron.craftcreatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText a_name,a_pass;
    Button log_in;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createDatabase();
         log_in=(Button)findViewById(R.id.login_button);
         a_name=(EditText) findViewById(R.id.login_input_name);
        a_pass=(EditText) findViewById(R.id.login_input_pass);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AdminName = a_name.getText().toString().trim();
                String AdminPassword = a_pass.getText().toString().trim();

                if (AdminName.equals("") || AdminPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill All fields", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RegisterAdmin WHERE AdminName='"+AdminName+"' and AdminPassword='"+ AdminPassword+"';", new String[]{});

                    Intent adActivity = new Intent(LoginActivity.this, Admin.class);
                    startActivity(adActivity);
                    Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    protected  void createDatabase()
    {
        sqLiteDatabase = openOrCreateDatabase("craftcreatures", Context.MODE_PRIVATE,null);
    }
}