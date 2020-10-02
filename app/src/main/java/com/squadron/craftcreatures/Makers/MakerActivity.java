package com.squadron.craftcreatures.Makers;

import androidx.appcompat.app.AppCompatActivity;

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
    Button maker_view;

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

        maker_view = (Button)findViewById((R.id.delivery_view_button));

        AddData();
    }

    public void AddData() {

        maker_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(maker_name.getText().toString(), maker_email.getText().toString(), maker_phone.getText().toString(), quantity.getText().toString(), unit_price.getText().toString(), buying_price.getText().toString() );
                if (isInserted == true) {
                    Toast.makeText(MakerActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MakerActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}