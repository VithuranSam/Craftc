package com.squadron.craftcreatures.Crafts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squadron.craftcreatures.R;

public class CraftActivity extends AppCompatActivity {
    Button ButtonAdd, ButtonEdit, ButtonDelete, ButtonSearch;
    EditText craftID, craftName, stock, price;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);

        ButtonSearch=(Button)findViewById(R.id.craft_search_button);
        ButtonAdd=(Button)findViewById(R.id.craft_add_button);
        ButtonEdit=(Button)findViewById(R.id.craft_edit_button);
        ButtonDelete=(Button)findViewById(R.id.craft_delete_button);
        craftID=(EditText)findViewById(R.id.craft_input_id);
        craftName=(EditText)findViewById(R.id.craft_input_name);
        stock=(EditText)findViewById(R.id.craft_input_stock);
        price=(EditText)findViewById(R.id.craft_input_price);

        ButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewData();
            }
        });

        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertINTODB();
            }
        });

        ButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditData();
            }
        });

        ButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData();
            }
        });
    }

    protected void createDatabase()
    {
        sqLiteDatabase=openOrCreateDatabase("craftcreatures", Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CraftsP(CraftID VARCHAR PRIMARY KEY NOT NULL, CraftName VARCHAR, Stock VARCHAR, Price VARCHAR);");
    }

    private void InsertINTODB() {
        String CraftID =craftID.getText().toString().trim();
        String CraftName =craftName.getText().toString().trim();
        String Stock =stock.getText().toString().trim();
        String Price =price.getText().toString().trim();
        if (CraftID.equals("") || CraftName.equals("") || Stock.equals("") || Price.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_LONG).show();
        }
        else {
            sqLiteDatabase.execSQL("INSERT INTO CraftsP values('"+CraftID+"','"+CraftName+"','"+Stock+"','"+Price+"');");
            Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
        }
    }

    private void ViewData() {
        String CraftID=craftID.getText().toString().trim();
        if(CraftID.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please enter CraftID",Toast.LENGTH_LONG).show();
        }
        else {
            String query= "Select * from CraftsP where CraftID='"+CraftID+"'";
            Cursor cursor=sqLiteDatabase.rawQuery(query, new  String[] {});
            if (cursor.moveToFirst()){
                do {
                    craftName.setText(cursor.getString(2));
                    stock.setText(cursor.getString(2));
                    price.setText(cursor.getString(2));
                }while (cursor.moveToNext());
            }
            Toast.makeText(getApplicationContext(),"Record found",Toast.LENGTH_LONG).show();
        }
    }

    private void EditData() {
        String CraftID=craftID.getText().toString().trim();
        String CraftName=craftName.getText().toString().trim();
        String Stock=stock.getText().toString().trim();
        String Price=price.getText().toString().trim();

        sqLiteDatabase.execSQL("update CraftsP set CraftName='"+CraftName+"',Stock='"+Stock+"',Price='"+Price+"' where CraftID='"+CraftID+"';");
        Toast.makeText(getApplicationContext(),"update Successfully", Toast.LENGTH_LONG).show();
    }

    private void DeleteData() {
        String CraftID=craftID.getText().toString().trim();
        if (CraftID.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please enter CraftID",Toast.LENGTH_LONG).show();
            return;
        }
        else{
            sqLiteDatabase.execSQL("delete from CraftsP where CraftID='"+CraftID+"';");
            Toast.makeText(getApplicationContext(),"deleted Successfully",Toast.LENGTH_LONG).show();
        }
    }








}