package com.squadron.craftcreatures.Crafts;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.squadron.craftcreatures.DatabaseHelperCraft;
import com.squadron.craftcreatures.R;

public class CraftActivity extends AppCompatActivity {
    DatabaseHelperCraft myDb;
    EditText cr_name,cr_price,cr_stock,cr_category,cr_description,craft_id;
    Button craft_add,craft_view,craft_edit,craft_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);
        myDb = new DatabaseHelperCraft(this);

        craft_id = (EditText)findViewById(R.id.craft_input_id);
        cr_name = (EditText)findViewById(R.id.craft_input_crname);
        cr_price = (EditText)findViewById(R.id.craft_input_price);
        cr_stock = (EditText)findViewById(R.id.craft_input_stock);
        cr_category = (EditText)findViewById(R.id.craft_input_category);
        cr_description = (EditText)findViewById(R.id.craft_input_description);

        craft_add = (Button)findViewById((R.id.craft_add_button));
        craft_edit = (Button)findViewById((R.id.craft_edit_button));
        craft_delete = (Button)findViewById((R.id.craft_delete_button));
        craft_view = (Button)findViewById((R.id.craft_view_button));
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

    }
    public  void AddData(){
        craft_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(cr_name.getText().toString(), cr_price.getText().toString(),cr_stock.getText().toString(), cr_category.getText().toString(),cr_description.getText().toString());
                if(isInserted == true){
                    Toast.makeText(CraftActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CraftActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewAll(){
        craft_view.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Cursor res = myDb.getAllData();
                                              if(res.getCount() == 0){
                                                  //Show Message
                                                  showMessage("Error ","Nothing Found");
                                                  return;
                                              }
                                              StringBuffer stringBuffer = new StringBuffer();
                                              while (res.moveToNext()){
                                                  stringBuffer.append("Craft ID : "+res.getString(0)+"\n");
                                                  stringBuffer.append("Craft Name : "+res.getString(1)+"\n");
                                                  stringBuffer.append("Craft Price : "+res.getString(2)+"\n");
                                                  stringBuffer.append("Craft Stock : "+res.getString(3)+"\n");
                                                  stringBuffer.append("Craft Category: "+res.getString(4)+"\n");
                                                  stringBuffer.append("Craft Description : "+res.getString(5)+"\n\n");
                                              }
                                              showMessage("Data", stringBuffer.toString());
                                          }
                                      }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        craft_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(craft_id.getText().toString(),cr_name.getText().toString(), cr_price.getText().toString(),cr_stock.getText().toString(), cr_category.getText().toString(),cr_description.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(CraftActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CraftActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DeleteData(){
        craft_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(craft_id.getText().toString());
                if(deletedRows > 0){
                    Toast.makeText(CraftActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CraftActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}