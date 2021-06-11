package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PaymentDetailsActivity extends AppCompatActivity {

    TextView email,mobile,payid,transid,amount,prodinfo,address,paymentstatus,datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        setTitle("Payment Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.tvemail);
        mobile = findViewById(R.id.TVmobile);
        payid = findViewById(R.id.TVpayuid);
        transid = findViewById(R.id.TVTrsnId);
        amount = findViewById(R.id.TVAmount);
        prodinfo = findViewById(R.id.TVprodinfo);
        address = findViewById(R.id.TVaddress);
        paymentstatus = findViewById(R.id.TVstatus);
        datetime = findViewById(R.id.TVdatetime);

        email.setText(getIntent().getStringExtra("email"));
        mobile.setText(getIntent().getStringExtra("mobileno"));
        payid.setText(getIntent().getStringExtra("payuid"));
        transid.setText(getIntent().getStringExtra("trsnid"));
        datetime.setText(getIntent().getStringExtra("datetime"));
        amount.setText(getIntent().getStringExtra("amount"));
        prodinfo.setText(getIntent().getStringExtra("prodinfo"));
        address.setText(getIntent().getStringExtra("address"));
        paymentstatus.setText(getIntent().getStringExtra("paymentstatu"));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}