package com.squadron.craftcreatures.Delivery;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squadron.craftcreatures.R;


public class DeliveryActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText cr_name,amount,unit_price,selling_price,cname,c_address,c_phone,delivery_id;
    LinearLayout delivery_add,delivery_edit,delivery_delete;
    Button delivery_view,search,cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        myDb = new DatabaseHelper(this);


        cr_name = (EditText)findViewById(R.id.delivery_input_name);
        amount = (EditText)findViewById(R.id.delivery_input_amount);
        unit_price = (EditText)findViewById(R.id.delivery_input_unit_price);
        selling_price = (EditText)findViewById(R.id.delivery_input_total_selling_price);
        c_address = (EditText)findViewById(R.id.delivery_input_caddress);
        c_phone = (EditText)findViewById(R.id.delivery_input_cphone);
        cname = (EditText)findViewById(R.id.delivery_input_cname);

        delivery_id = (EditText)findViewById(R.id.delivery_input_id);// Lately Add

        delivery_add = (LinearLayout)findViewById((R.id.delivery_add_button));
        delivery_edit = (LinearLayout)findViewById((R.id.delivery_edit_button));
        delivery_delete = (LinearLayout)findViewById((R.id.delivery_delete_button));

        delivery_view = (Button)findViewById((R.id.delivery_view_button));
        search = (Button)findViewById((R.id.delivery_search_view));
        cal = (Button)findViewById((R.id.delivery_cal_total));

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
        SearchData();

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().length() == 0 ){
                    amount.setText("0");
                }

                if (unit_price.getText().toString().length() == 0 ){
                    amount.setText("0");
                }
                selling_price.setText(String.valueOf(calculate()));
            }


        });
    }

    public int calculate() {
        int amountCraft = Integer.parseInt(amount.getText().toString());
        int unit_p = Integer.parseInt(unit_price.getText().toString());

        int cal = amountCraft * unit_p;
        return cal;
    }

    public  void AddData(){
        delivery_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(cr_name.getText().toString(), amount.getText().toString(),unit_price.getText().toString(),selling_price.getText().toString(),cname.getText().toString(),c_address.getText().toString(),c_phone.getText().toString());
                if(isInserted == true){
                    Toast.makeText(DeliveryActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
                else{
                    Toast.makeText(DeliveryActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewAll(){
        delivery_view.setOnClickListener(new View.OnClickListener() {
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
                                                  stringBuffer.append("Delivery ID : "+res.getString(0)+"\n");
                                                  stringBuffer.append("Delivery Item Name : "+res.getString(1)+"\n");
                                                  stringBuffer.append("Delivery Item Amount : "+res.getString(2)+"\n");
                                                  stringBuffer.append("Delivery Item's Unit price : "+res.getString(3)+"\n");
                                                  stringBuffer.append("Delivery Item Total Selling Price  : "+res.getString(4)+"\n");
                                                  stringBuffer.append("Customer Name : "+res.getString(5)+"\n");
                                                  stringBuffer.append("Customer Address : "+res.getString(6)+"\n");
                                                  stringBuffer.append("Customer Phone No : "+res.getString(7)+"\n\n");
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
        delivery_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(delivery_id.getText().toString(),cr_name.getText().toString(), amount.getText().toString(),unit_price.getText().toString(),selling_price.getText().toString(),cname.getText().toString(),c_address.getText().toString(),c_phone.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(DeliveryActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
                else {
                    Toast.makeText(DeliveryActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DeleteData(){
        delivery_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(delivery_id.getText().toString());
                if(deletedRows > 0){
                    Toast.makeText(DeliveryActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
                else {
                    Toast.makeText(DeliveryActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SearchData(){

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDb.searchData(delivery_id.getText().toString());
                if (data.getCount() == 0) {
                    //Show Message
                    showMessage("Error ", "Nothing Found");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (data.moveToNext()) {
                    delivery_id.setText( data.getString(0));
                    cr_name.setText( data.getString(1));
                    amount.setText( data.getString(2));
                    unit_price.setText( data.getString(3));
                    selling_price.setText( data.getString(4));
                    cname.setText( data.getString(5));
                    c_address.setText( data.getString(6));
                    c_phone.setText( data.getString(7));
                }
            }
        });

    }

    private void clearControls(){
       delivery_id.setText("");
        cr_name.setText("");
        amount.setText("");
        unit_price.setText("");
        selling_price.setText("");
        cname.setText("");
        c_address.setText("");
        c_phone.setText("");
    }
}