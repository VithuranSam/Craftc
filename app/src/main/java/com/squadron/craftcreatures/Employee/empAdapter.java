package com.squadron.craftcreatures.Employee;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squadron.craftcreatures.R;

import java.util.ArrayList;

import static com.squadron.craftcreatures.Employee.EmployeeActivity.cal_tax;

public class empAdapter extends RecyclerView.Adapter<empViewHolder> implements Filterable {

    private Context context;
    private ArrayList<emp> listemp;
    private ArrayList<emp> mArrayList;

    private SqliteDatabase mDatabase;


    public empAdapter(Context context, ArrayList<emp> listemp) {
        this.context = context;
        this.listemp = listemp;
        this.mArrayList=listemp;
        mDatabase = new SqliteDatabase(context);
    }

    @Override
    public empViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_list_layout, parent, false);
        return new empViewHolder(view);
    }

    @Override
    public void onBindViewHolder(empViewHolder holder, int position) {
        final emp emp = listemp.get(position);

        holder.name.setText(emp.getName());
        holder.jobtitle.setText(emp.getJobtitle());
        holder.salary.setText(emp.getSalary());
        holder.ph_no.setText(emp.getPhno());
        holder.email.setText(emp.getEmail());

        holder.editemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(emp);
            }
        });

        holder.deleteemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteemp(emp.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    listemp = mArrayList;
                } else {

                    ArrayList<emp> filteredList = new ArrayList<>();

                    for (emp emp : mArrayList) {

                        if (emp.getName().toLowerCase().contains(charString)) {

                            filteredList.add(emp);
                        }
                    }

                    listemp = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listemp;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listemp = (ArrayList<emp>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public int getItemCount() {
        return listemp.size();
    }


    private void editTaskDialog(final emp emp){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_emp_layout, null);

        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText jobField = (EditText)subView.findViewById(R.id.jobtitle);
        final EditText salaryField = (EditText)subView.findViewById(R.id.salary);
        final EditText phoneField = (EditText)subView.findViewById(R.id.enter_phno);
        final EditText emailField = (EditText)subView.findViewById(R.id.email);

        if(emp != null){
            nameField.setText(emp.getName());
            jobField.setText(String.valueOf(emp.getJobtitle()));
            salaryField.setText(String.valueOf(emp.getSalary()));
            phoneField.setText(String.valueOf(emp.getPhno()));
            emailField.setText(String.valueOf(emp.getEmail()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT EMPLOYEE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int sal = Integer.parseInt(salaryField.getText().toString());
                int salf =  cal_tax(sal);
                final String name = nameField.getText().toString();
                final String jobtitle = jobField.getText().toString();
                final String salary = String.valueOf(salf);
                final String ph_no = phoneField.getText().toString();
                final String email = emailField.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateemp(new emp(emp.getId(), name, jobtitle, salary, ph_no, email));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}