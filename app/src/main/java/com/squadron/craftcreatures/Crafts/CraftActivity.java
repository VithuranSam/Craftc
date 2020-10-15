package com.squadron.craftcreatures.Crafts;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.squadron.craftcreatures.R;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class CraftActivity extends AppCompatActivity {
    DatabaseHelperCraft myDb;
    EditText cr_name, cr_actual_price,cr_selling_price,profit, cr_stock, cr_category, cr_description, craft_id;
    LinearLayout craft_add, craft_edit, craft_delete;
    Button craft_view,cal_craft,search;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft);
        myDb = new DatabaseHelperCraft(this);
        awesomeValidation = new AwesomeValidation(BASIC);

        craft_id = (EditText) findViewById(R.id.craft_input_id);
        cr_name = (EditText) findViewById(R.id.craft_input_crname);
        cr_actual_price = (EditText) findViewById(R.id.craft_input_actual_price);
        cr_selling_price = (EditText) findViewById(R.id.craft_input_selling_price);
        profit = (EditText) findViewById(R.id.craft_input_profit);
        cr_stock = (EditText) findViewById(R.id.craft_input_stock);
        cr_category = (EditText) findViewById(R.id.craft_input_category);
        cr_description = (EditText) findViewById(R.id.craft_input_description);

        craft_add = (LinearLayout) findViewById(R.id.craft_add_button);
        craft_view = (Button) findViewById((R.id.craft_view_button));
        craft_edit = (LinearLayout) findViewById((R.id.craft_edit_button));
        craft_delete = (LinearLayout) findViewById((R.id.craft_delete_button));
        cal_craft = (Button) findViewById((R.id.craft_cal_profit));
        search = (Button) findViewById((R.id.craft_search_view));

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
        SearchData();

        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_crname, "^[a-zA-Z]{2,30}$+", R.string.err_name);
        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_actual_price, "^[0-9]{1,20}$+", R.string.err_ap);
        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_selling_price, "^[0-9]{1,20}$+", R.string.err_sp);
        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_profit, "^[0-9]{1,20}$+", R.string.err_prof);
        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_stock, "^[0-9]{1,20}$+", R.string.err_sto);
        awesomeValidation.addValidation(CraftActivity.this, R.id.craft_input_category, "^[a-zA-Z]{2,100}$+", R.string.err_cat);

        cal_craft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cr_actual_price.getText().toString().length() == 0 ){
                    cr_actual_price.setText("0");
                }

                if (cr_selling_price.getText().toString().length() == 0 ){
                    cr_selling_price.setText("0");
                }
                int actualPrice = Integer.parseInt(cr_actual_price.getText().toString());
                int sellingPrice = Integer.parseInt(cr_selling_price.getText().toString());
                profit.setText(String.valueOf(calculate(sellingPrice,actualPrice)));
            }


        });
    }

    public static int calculate(int sellingPrice,int actualPrice) {


        int profit = sellingPrice - actualPrice;
        return profit;
    }


    public void AddData() {

        craft_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate() == true) {
                    boolean isInserted = myDb.insertData(cr_name.getText().toString(),
                            cr_actual_price.getText().toString(),
                            cr_selling_price.getText().toString(),
                            profit.getText().toString(),
                            cr_stock.getText().toString(),
                            cr_category.getText().toString(),
                            cr_description.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(CraftActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        clearControls();
                    } else {
                        Toast.makeText(CraftActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void ViewAll() {

        craft_view.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Cursor res = myDb.getAllData();
                                              if (res.getCount() == 0) {
                                                  //Show Message
                                                  showMessage("Error ", "Nothing Found");
                                                  return;
                                              }
                                              StringBuffer stringBuffer = new StringBuffer();
                                              while (res.moveToNext()) {
                                                  stringBuffer.append("Craft ID : " + res.getString(0) + "\n");
                                                  stringBuffer.append("Craft Name : " + res.getString(1) + "\n");
                                                  stringBuffer.append("Craft Actual Price : " + res.getString(2) + "\n");
                                                  stringBuffer.append("Craft Selling Price : " + res.getString(3) + "\n");
                                                  stringBuffer.append("Profit : " + res.getString(4) + "\n");
                                                  stringBuffer.append("Craft Stock : " + res.getString(5) + "\n");
                                                  stringBuffer.append("Craft Category: " + res.getString(6) + "\n");
                                                  stringBuffer.append("Craft Description : " + res.getString(7) + "\n\n");
                                              }
                                              showMessage("Data", stringBuffer.toString());
                                          }
                                      }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData() {

        craft_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate() == true) {
                    boolean isUpdate = myDb.updateData(craft_id.getText().toString(),
                            cr_name.getText().toString(), cr_actual_price.getText().toString(),
                            cr_selling_price.getText().toString(), profit.getText().toString(),
                            cr_stock.getText().toString(), cr_category.getText().toString(),
                            cr_description.getText().toString());
                    if (isUpdate == true) {
                        Toast.makeText(CraftActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        clearControls();
                    } else {
                        Toast.makeText(CraftActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void DeleteData() {

        craft_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(craft_id.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(CraftActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    clearControls();
                } else {
                    Toast.makeText(CraftActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void SearchData(){

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDb.searchData(craft_id.getText().toString());
                if (data.getCount() == 0) {
                    //Show Message
                    showMessage("Error ", "Nothing Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (data.moveToNext()) {
                    craft_id.setText( data.getString(0));
                    cr_name.setText( data.getString(1));
                    cr_actual_price.setText( data.getString(2));
                    cr_selling_price.setText( data.getString(3));
                    profit.setText( data.getString(4));
                    cr_stock.setText( data.getString(5));
                    cr_category.setText( data.getString(6));
                    cr_description.setText( data.getString(7));
                }
            }
        });

    }
    private void clearControls(){
        craft_id.setText("");
        cr_name.setText("");
        cr_actual_price.setText("");
        cr_selling_price.setText("");
        profit.setText("");
        cr_stock.setText("");
        cr_category.setText("");
        cr_description.setText("");
    }
}