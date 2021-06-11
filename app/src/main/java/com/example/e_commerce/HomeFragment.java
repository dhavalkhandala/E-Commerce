package com.example.e_commerce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapter.ironToyProductListAdapter;
import com.example.e_commerce.Model.ProductAddModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ironToyProductListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recyclerirontoy);

        Query query = FirebaseFirestore.getInstance().collection("Products").whereEqualTo("category","IronToy");
        FirestoreRecyclerOptions<ProductAddModel> rvOptions=new FirestoreRecyclerOptions.Builder<ProductAddModel>()
                .setQuery(query,ProductAddModel.class).build();

        adapter=new ironToyProductListAdapter(rvOptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
