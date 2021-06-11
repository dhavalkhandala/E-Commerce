package com.example.e_commerce.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Model.PAymentModel;
import com.example.e_commerce.PaymentDetailsActivity;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PaymentHistoryAdapter extends FirestoreRecyclerAdapter<PAymentModel,PaymentHistoryAdapter.PaymentViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PaymentHistoryAdapter(@NonNull FirestoreRecyclerOptions<PAymentModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PaymentViewHolder holder, int position, @NonNull PAymentModel model) {

        holder.email.setText(model.getUserEmail());
        holder.payuid.setText(model.getPaymentId());
        holder.time.setText(model.getDatetime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), PaymentDetailsActivity.class);
                intent.putExtra("email",model.getUserEmail());
                intent.putExtra("payuid",model.getPaymentId());
                intent.putExtra("trsnid",model.getTransactionid());
                intent.putExtra("datetime",model.getDatetime());
                intent.putExtra("address",model.getAddress());
                intent.putExtra("mobileno",model.getPhone());
                intent.putExtra("prodinfo",model.getProductName()+" * "+model.getQuantity());
                intent.putExtra("paymentstatu",model.getPayment_Status());
                intent.putExtra("amount",model.getTotal_Price());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentdetailsview,parent,false);
        return new PaymentViewHolder(view);
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView email,payuid,time;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.useremail);
            payuid = itemView.findViewById(R.id.paymentid);
            time = itemView.findViewById(R.id.pymenttime);
        }
    }
}
