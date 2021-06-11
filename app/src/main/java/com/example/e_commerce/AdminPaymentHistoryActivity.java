package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce.Adapter.PaymentHistoryAdapter;
import com.example.e_commerce.Model.PAymentModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminPaymentHistoryActivity extends AppCompatActivity {
    PaymentHistoryAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_payment_history);

        setTitle("Payment History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.RVpaymenthistory);
        Query query = FirebaseFirestore.getInstance().collection("Payment Details");
        FirestoreRecyclerOptions<PAymentModel> rvOptions=new FirestoreRecyclerOptions.Builder<PAymentModel>()
                .setQuery(query, PAymentModel.class).build();

        adapter=new PaymentHistoryAdapter(rvOptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}