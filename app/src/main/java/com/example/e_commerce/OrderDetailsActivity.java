package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView uemail,pname,detals,date,status;
    Button prepared,deliverd;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        setTitle("Order Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();

            uemail = findViewById(R.id.uemail);
            pname = findViewById(R.id.pname);
            detals = findViewById(R.id.Pdetails);
            date = findViewById(R.id.orderdt);
            status = findViewById(R.id.paymentst);
            prepared = findViewById(R.id.preparedbtn);
            deliverd = findViewById(R.id.deliveredbtn);
            imageView = findViewById(R.id.imageView3);

            uemail.setText(getIntent().getStringExtra("username"));
            pname.setText(getIntent().getStringExtra("productname"));
            detals.setText(getIntent().getStringExtra("productprice") + " * "
                    + getIntent().getStringExtra("quntity") + " = " + getIntent().getStringExtra("totalprice"));
            date.setText(getIntent().getStringExtra("orderdate"));
            status.setText(getIntent().getStringExtra("paymentstatus"));
            Glide.with(OrderDetailsActivity.this).load(getIntent().getStringExtra("productimage")).into(imageView);
            prepared.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore.getInstance().collection("Users").document(userEmail)
                            .collection("Orders").document(getIntent().getStringExtra("orderid"))
                            .update("delivery_Status","Prepared......")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        FirebaseFirestore.getInstance().collection("Order")
                                                .document(getIntent().getStringExtra("orderid"))
                                                .update("delivery_Status","Prepared......")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(OrderDetailsActivity.this, "Update.....", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }
                            });
                }
            });
            deliverd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore.getInstance().collection("Users").document(userEmail)
                            .collection("Orders").document(getIntent().getStringExtra("orderid"))
                            .update("delivery_Status","Delivered......")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        FirebaseFirestore.getInstance().collection("Order")
                                                .document(getIntent().getStringExtra("orderid"))
                                                .update("delivery_Status","Delivered......")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(OrderDetailsActivity.this, "Update.....", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }
                            });
                }
            });

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}