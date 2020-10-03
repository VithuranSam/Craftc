package com.squadron.craftcreatures.Makers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.squadron.craftcreatures.R;

public class MakerActivity extends AppCompatActivity {
    DatabaseHelperMaker myDb;
    EditText maker_name,maker_email,maker_phone,quantity,unit_price,maker_id,buying_price;
    LinearLayout maker_add,maker_edit,maker_delete;
    Button maker_view,cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker);
        myDb = new DatabaseHelperMaker(this);

        maker_name = (EditText)findViewById(R.id.maker_input_name);
        maker_email = (EditText)findViewById(R.id.maker_input_email);
        maker_phone = (EditText)findViewById(R.id.maker_input_phone);
        quantity = (EditText)findViewById(R.id.maker_input_quantity);
        unit_price = (EditText)findViewById(R.id.maker_input_unit_price);
        buying_price = (EditText)findViewById(R.id.maker_input_buying_price);

        maker_id = (EditText)findViewById(R.id.maker_input_id);

        maker_add = (LinearLayout)findViewById((R.id.maker_add_button));
        maker_edit = (LinearLayout)findViewById((R.id.maker_edit_button));
        maker_delete = (LinearLayout)findViewById((R.id.maker_delete_button));

        maker_view = (Button)findViewById((R.id.maker_view_button));
        cal = (Button)findViewById(R.id.cal_total) ;

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity.getText().toString().length() == 0 ){
                    quantity.setText("0");
                }

                if (unit_price.getText().toString().length() == 0 ){
                    quantity.setText("0");
                }
                buying_price.setText(String.valueOf(calculate()));
            }


        });

    }

    public int calculate() {
        int qu = Integer.parseInt(quantity.getText().toString());
        int unit_p = Integer.parseInt(unit_price.getText().toString());

        int cal = qu * unit_p;
        return cal;
    }

    public void AddData() {

        maker_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(maker_name.getText().toString(), maker_email.getText().toString(), maker_phone.getText().toString(), quantity.getText().toString(), unit_price.getText().toString(), buying_price.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MakerActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    clearControls();
                } else {
                    Toast.makeText(MakerActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void ViewAll() {

        maker_view.setOnClickListener(new View.OnClickListener() {
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
                                                  stringBuffer.append("Makers / Company ID : " + res.getString(0) + "\n");
                                                  stringBuffer.append("Makers / Company Name : " + res.getString(1) + "\n");
                                                  stringBuffer.append("Email Address : " + res.getString(2) + "\n");
                                                  stringBuffer.append("Phone No : " + res.getString(3) + "\n");
                                                  stringBuffer.append("Craft Quantity: " + res.getString(4) + "\n");
                                                  stringBuffer.append("Craft Unit Price : " + res.getString(5) + "\n\n");
                                                  stringBuffer.append("Craft Buying Price : " + res.getString(6) + "\n\n");
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

        maker_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(maker_id.getText().toString(), maker_name.getText().toString(), maker_email.getText().toString(), maker_phone.getText().toString(), quantity.getText().toString(), unit_price.getText().toString(), buying_price.getText().toString() );
                if (isUpdate == true) {
                    Toast.makeText(MakerActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    clearControls();
                } else {
                    Toast.makeText(MakerActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DeleteData() {

        maker_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(maker_id.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(MakerActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    clearControls();
                } else {
                    Toast.makeText(MakerActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void clearControls(){
        maker_id.setText("");
        maker_name.setText("");
        maker_email.setText("");
        maker_phone.setText("");
        quantity.setText("");
        unit_price.setText("");
        buying_price.setText("");
    }

}