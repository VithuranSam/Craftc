package com.squadron.craftcreatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.LinearLayout;

import com.squadron.craftcreatures.Crafts.CraftActivity;
import com.squadron.craftcreatures.Delivery.DeliveryActivity;
import com.squadron.craftcreatures.Employee.EmployeeActivity;

public class Admin extends AppCompatActivity {
    LinearLayout employee;
    LinearLayout crafts;
    LinearLayout delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.setTitle("Craft.lk");

        employee = (LinearLayout) findViewById(R.id.employee_layout);
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
                startActivity(intent);
            }
        });

        crafts = (LinearLayout) findViewById(R.id.craft_layout);
        crafts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CraftActivity.class);
                startActivity(intent);
            }
        });

        delivery = (LinearLayout) findViewById(R.id.deliver_layout);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeliveryActivity.class);
                startActivity(intent);
            }
        });
    }
}