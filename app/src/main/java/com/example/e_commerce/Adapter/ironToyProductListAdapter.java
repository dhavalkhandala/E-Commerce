package com.example.e_commerce.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.Model.ProductAddModel;
import com.example.e_commerce.ProductDetailsActivity;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ironToyProductListAdapter extends FirestoreRecyclerAdapter<ProductAddModel, ironToyProductListAdapter.ListViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ironToyProductListAdapter(@NonNull FirestoreRecyclerOptions<ProductAddModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListViewHolder holder, int position, @NonNull ProductAddModel model) {
        holder.textView.setText(model.getProduct_Name());
        Glide.with(holder.itemView.getContext()).load(model.getProduct_Image()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailsActivity.class);
                intent.putExtra("pname",model.getProduct_Name());
                intent.putExtra("price",model.getProduct_Price());
                intent.putExtra("pimage",model.getProduct_Image());
                intent.putExtra("desc",model.getProduct_Desc());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.irontoyproductlist,parent,false);
        return new ListViewHolder(view);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView4);
            textView = itemView.findViewById(R.id.name);
        }
    }
}
