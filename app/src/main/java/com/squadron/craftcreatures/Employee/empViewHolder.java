package com.squadron.craftcreatures.Employee;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squadron.craftcreatures.R;

public class empViewHolder extends RecyclerView.ViewHolder {
    public TextView name,ph_no,salary,jobtitle,email;
    public ImageView deleteemp;
    public  ImageView editemp;

    public empViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.contact_name);
        jobtitle = (TextView)itemView.findViewById(R.id.jobtitle);
        salary = (TextView)itemView.findViewById(R.id.salary);
        ph_no = (TextView)itemView.findViewById(R.id.ph_no);
        email = (TextView)itemView.findViewById(R.id.email);
        deleteemp = (ImageView)itemView.findViewById(R.id.delete_contact);
        editemp = (ImageView)itemView.findViewById(R.id.edit_contact);
    }
}

