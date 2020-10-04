package com.squadron.craftcreatures.Employee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squadron.craftcreatures.R;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {

    private static final String TAG = EmployeeActivity.class.getSimpleName();

    private SqliteDatabase mDatabase;
    private ArrayList<emp> allemp=new ArrayList<>();
    private empAdapter mAdapter;

    public int my_sal = 0;

    public static int cal_tax (int sal){
        if(sal > 100000){
            sal = (int) (sal - (sal * 0.05));
        }else {sal =sal ;}
        return sal;
    
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);

        RecyclerView empView = (RecyclerView)findViewById(R.id.employee_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        empView.setLayoutManager(linearLayoutManager);
        empView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        allemp = mDatabase.listemp();

        if(allemp.size() > 0){
            empView.setVisibility(View.VISIBLE);
            mAdapter = new empAdapter(this, allemp);
            empView.setAdapter(mAdapter);

        }else {
            empView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no employee in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_emp_layout, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText salaryField = (EditText)subView.findViewById(R.id.salary);
        final EditText jobtitleField = (EditText)subView.findViewById(R.id.jobtitle);
        final EditText noField = (EditText)subView.findViewById(R.id.enter_phno);
        final EditText emailField = (EditText)subView.findViewById(R.id.email);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADD employee", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int sal = Integer.parseInt(salaryField.getText().toString());
                int salf =  cal_tax(sal);

                final String name = nameField.getText().toString();
                final String salary = String.valueOf(sal);
                final String jobtitle = jobtitleField.getText().toString();
                final String ph_no = noField.getText().toString();
                final String email = emailField.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(EmployeeActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    emp newemp = new emp(name, jobtitle,salary,ph_no,email);

                    mDatabase.addemp(newemp);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(EmployeeActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

}