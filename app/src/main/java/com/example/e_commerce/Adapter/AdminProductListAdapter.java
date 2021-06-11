package com.example.e_commerce.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.Model.ProductAddModel;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminProductListAdapter extends FirestoreRecyclerAdapter<ProductAddModel,AdminProductListAdapter.ListViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    AlertDialog.Builder builder;
    public AdminProductListAdapter(@NonNull FirestoreRecyclerOptions<ProductAddModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListViewHolder holder, int position, @NonNull ProductAddModel model) {
        builder = new AlertDialog.Builder(holder.itemView.getContext());
        holder.prodTitle.setText(model.getProduct_Name());
        holder.prodPrice.setText(String.valueOf(model.getProduct_Price()));
        Glide.with(holder.prodImage.getContext()).load(model.getProduct_Image()).into(holder.prodImage);
        holder.deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to Remove "+ model.getProduct_Name() +" ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseFirestore.getInstance().collection("Products").document(model.getProduct_Name()).delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(holder.deleteBTN.getContext(), "Delete Product", Toast.LENGTH_SHORT).show();
                                                }else
                                                {
                                                    Toast.makeText(holder.deleteBTN.getContext(),""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Delete Product");
                alert.show();
            }
        });
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminproduct_list,parent,false);
        return new ListViewHolder(view);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteBTN,prodImage;
        TextView prodTitle,prodPrice;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBTN = itemView.findViewById(R.id.delete_btnpro);
            prodImage = itemView.findViewById(R.id.onlineProductImage);
            prodTitle = itemView.findViewById(R.id.onlineProductTitlename);
            prodPrice = itemView.findViewById(R.id.onlineProductPrice);
        }
    }
}
