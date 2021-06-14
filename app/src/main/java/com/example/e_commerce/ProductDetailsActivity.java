package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    int rr;
    TextView namee,prices,descr,res,tt;
    ImageView pimg,addd,subb;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        setTitle("Product Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namee = findViewById(R.id.pname);
        prices = findViewById(R.id.Pprice);
        descr = findViewById(R.id.desc);
        pimg = findViewById(R.id.imageView3);
        res = findViewById(R.id.quantity);
        addd = findViewById(R.id.addbtn);
        subb = findViewById(R.id.subbtn);
        btn = findViewById(R.id.btnpayu);
        tt = findViewById(R.id.ttprice);

        res.setText("1");
        Glide.with(ProductDetailsActivity.this).load(getIntent().getStringExtra("pimage")).into(pimg);
        namee.setText(getIntent().getStringExtra("pname"));
        prices.setText(getIntent().getStringExtra("price"));
        descr.setText(getIntent().getStringExtra("desc"));
        tt.setText(getIntent().getStringExtra("price"));
        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = Integer.parseInt(prices.getText().toString());
                int num = Integer.parseInt(res.getText().toString());
                if (num < 10) {
                    num++;
                    rr = price*num;
                    res.setText(String.valueOf(num));
                    tt.setText(String.valueOf(rr));

                }else {
                    Toast.makeText(ProductDetailsActivity.this, "That's okay", Toast.LENGTH_SHORT).show();
                }

            }
        });
        subb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = Integer.parseInt(prices.getText().toString());
                int num = Integer.parseInt(res.getText().toString());
                if (num > 1){
                    num--;
                    rr = price*num;
                    res.setText(String.valueOf(num));
                    tt.setText(String.valueOf(rr));
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailsActivity.this,ShippingActivity.class);
                intent.putExtra("pname",getIntent().getStringExtra("pname"));
                intent.putExtra("price",getIntent().getStringExtra("price"));
                intent.putExtra("desc",getIntent().getStringExtra("desc"));
                intent.putExtra("pimage",getIntent().getStringExtra("pimage"));
                intent.putExtra("quantity",res.getText().toString());
                intent.putExtra("total price",String.valueOf(rr));
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}