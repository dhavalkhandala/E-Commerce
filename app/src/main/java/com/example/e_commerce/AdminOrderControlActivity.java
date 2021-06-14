package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce.Adapter.AdminOrderListAdapter;
import com.example.e_commerce.Adapter.ironToyProductListAdapter;
import com.example.e_commerce.Model.OrderModel;
import com.example.e_commerce.Model.ProductAddModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminOrderControlActivity extends AppCompatActivity {

    AdminOrderListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_control);

        setTitle("Order Progress");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        Query query = FirebaseFirestore.getInstance().collection("Order").orderBy("datetime", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<OrderModel> rvOptions=new FirestoreRecyclerOptions.Builder<OrderModel>()
                .setQuery(query,OrderModel.class).build();

        adapter=new AdminOrderListAdapter(rvOptions);
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