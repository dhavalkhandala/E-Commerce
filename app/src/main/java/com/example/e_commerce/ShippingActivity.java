package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class ShippingActivity extends AppCompatActivity {

    EditText phone,address,city;
    Button next;
    boolean valid;
    String phonee,addresss,cityy,pname,price,desc,pimage,qu,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        setTitle("Shipping Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phone = findViewById(R.id.input_phone);
        address = findViewById(R.id.input_address);
        city = findViewById(R.id.input_City);
        next = findViewById(R.id.btnnext);

        phonee = phone.getText().toString();
        addresss = address.getText().toString();
        cityy = city.getText().toString();
        pname = getIntent().getStringExtra("pname");
        price = getIntent().getStringExtra("price");
        desc = getIntent().getStringExtra("desc");
        pimage = getIntent().getStringExtra("pimage");
        qu = getIntent().getStringExtra("quantity");
        total = getIntent().getStringExtra("total price");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid == true) {

                    int price = Integer.parseInt(getIntent().getStringExtra("price"));
                    int num = Integer.parseInt(getIntent().getStringExtra("quantity"));
                    int res = price*num;

                    Intent intent = new Intent(ShippingActivity.this,PaymentActivity.class);
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("address",address.getText().toString());
                    intent.putExtra("city",city.getText().toString());
                    intent.putExtra("pname",getIntent().getStringExtra("pname"));
                    intent.putExtra("price",getIntent().getStringExtra("price"));
                    intent.putExtra("desc",getIntent().getStringExtra("desc"));
                    intent.putExtra("pimage",getIntent().getStringExtra("pimage"));
                    intent.putExtra("quantity",getIntent().getStringExtra("quantity"));
                    intent.putExtra("total price",String.valueOf(res));
                    startActivity(intent);
//                    Log.d("data","ProductName :"+getIntent().getStringExtra("pname")+"\n"+
//                            "PRoductPrice :"+getIntent().getStringExtra("price")+"\n"+
//                            "Product Desc :"+getIntent().getStringExtra("desc")+"\n"+
//                            "Product Image :"+getIntent().getStringExtra("pimage")+"\n"+
//                            "quan :"+getIntent().getStringExtra("quantity")+"\n"+
//                            "total :"+res);
                }else {
                    validate();
                }
            }
        });
    }
    public boolean validate() {

        String addres = address.getText().toString();
        String ct = city.getText().toString();
        String number = phone.getText().toString();

        if (addres.isEmpty()) {
            address.setError("Please Enter address.");
            address.setFocusable(true);
            valid = false;
        } else {
            valid = true;
        }

        if (number.isEmpty()) {
            phone.setError("Mobile number can't empty");
            phone.setFocusable(true);
            valid = false;
        } else {
            valid = true;
        }
        if (ct.isEmpty()) {
            city.setError("Mobile number can't empty");
            city.setFocusable(true);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}