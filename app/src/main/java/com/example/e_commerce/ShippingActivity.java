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

    EditText phone,address,state,city,ccp,flats,colonyy,pinn;
    Button next;
    String phonee,addresss,cityy,pname,price,desc,pimage,qu,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        setTitle("Shipping Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phone = findViewById(R.id.mobile);
        ccp = findViewById(R.id.ccp);
        flats = findViewById(R.id.input_flat);
        city = findViewById(R.id.city);
        colonyy = findViewById(R.id.input_streetcolony);
        state = findViewById(R.id.state);
        pinn = findViewById(R.id.input_pin);

        next = findViewById(R.id.btnnext);

        phonee = ccp.getText().toString() +" "+ phone.getText().toString();
        addresss = flats.getText().toString()+", "+colonyy.getText().toString()+", "
                    +city.getText().toString()+", "+state.getText().toString()+", "+pinn.getText().toString();
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

                String valid = "true";
                if (phone.getText().toString().equals("") || phone.getText().toString().length() <10 ||phone.getText().toString().equals(null) ) {
                    phone.setError("Please Enter Valid Mobile Number");
                    valid = "false";
                }
                if (flats.getText().toString().equals("") || flats.getText().toString().equals(null)) {
                    flats.setError("Please Enter Flat/ House No/ Building name");
                    valid = "false";
                }
                if (city.getText().toString().equals("") || city.getText().toString().equals(null)) {
                    city.setError("Please Enter City Name");
                    valid = "false";
                }
                if (colonyy.getText().toString().equals("") || colonyy.getText().toString().equals(null)) {
                    colonyy.setError("Please Enter Street/ Colony Name");
                    valid = "false";
                }
                if (state.getText().toString().equals("") || state.getText().toString().equals(null)) {
                    state.setError("Please Enter State Name");
                    valid = "false";
                }
                if (pinn.getText().toString().equals("") || pinn.getText().toString().length() < 6 || pinn.getText().toString().equals(null)) {
                    pinn.setError("Please Enter Valid Pin Code");
                    valid = "false";
                }
                if (valid=="true") {

                    int price = Integer.parseInt(getIntent().getStringExtra("price"));
                    int num = Integer.parseInt(getIntent().getStringExtra("quantity"));
                    int res = price * num;

                    Intent intent = new Intent(ShippingActivity.this, PaymentActivity.class);
                    intent.putExtra("phone", ccp.getText().toString() + " " + phone.getText().toString());
                    intent.putExtra("address", flats.getText().toString() + ", " + colonyy.getText().toString() + ", "
                            + city.getText().toString() + ", " + state.getText().toString() + ", " + pinn.getText().toString());
                    intent.putExtra("city", city.getText().toString());
                    intent.putExtra("pname", getIntent().getStringExtra("pname"));
                    intent.putExtra("price", getIntent().getStringExtra("price"));
                    intent.putExtra("desc", getIntent().getStringExtra("desc"));
                    intent.putExtra("pimage", getIntent().getStringExtra("pimage"));
                    intent.putExtra("quantity", getIntent().getStringExtra("quantity"));
                    intent.putExtra("total price", String.valueOf(res));
                    startActivity(intent);
                }
//                    Log.d("data","ProductName :"+getIntent().getStringExtra("pname")+"\n"+
//                            //"PRoductPrice :"+getIntent().getStringExtra("price")+"\n"+
//                            "Product Desc :"+getIntent().getStringExtra("desc")+"\n"+
//                            "Product Image :"+getIntent().getStringExtra("pimage")+"\n"+
//                            //"quan :"+getIntent().getStringExtra("quantity")+"\n"+
////                            "total :"+res+"\n"+
//                            "address :"+flats.getText().toString()+", "+colonyy.getText().toString()+", "
//                            +city.getText().toString()+", "+state.getText().toString()+", "+pinn.getText().toString()+"\n"+
//                            "city :"+ city.getText().toString()+"\n"+
//                            "phone :"+ ccp.getText().toString() +" "+ phone.getText().toString());
            }
        });
    }
//
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}