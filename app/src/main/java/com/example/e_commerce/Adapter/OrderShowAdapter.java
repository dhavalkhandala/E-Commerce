package com.example.e_commerce.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Model.OrderModel;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class OrderShowAdapter extends FirestoreRecyclerAdapter<OrderModel,OrderShowAdapter.OrderShowViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderShowAdapter(@NonNull FirestoreRecyclerOptions<OrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderShowViewHolder holder, int position, @NonNull OrderModel model) {
        String pendding = "\\u23F3";
        holder.pname.setText(model.getProductName());
        holder.orderid.setText(model.getOrderID());
        holder.orderdate.setText(model.getDatetime());
        holder.paymentst.setText(model.getPayment_Status());
        holder.deliveryst.setText(model.getDelivery_Status());
        if (model.getDelivery_Status().equals("Pending......")){
            holder.deliveryst.setTextColor(Color.RED);
        }else if (model.getDelivery_Status().equals("Prepared......")){
            holder.deliveryst.setTextColor(Color.parseColor("#ff8c1a"));
        }else if (model.getDelivery_Status().equals("Delivered......")){
            holder.deliveryst.setTextColor(Color.parseColor("#00b33c"));
        }
    }

    @NonNull
    @Override
    public OrderShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderproducts,parent,false);
        return new OrderShowViewHolder(view);
    }

    public class OrderShowViewHolder extends RecyclerView.ViewHolder {
        TextView pname,orderid,orderdate,paymentst,deliveryst;

        public OrderShowViewHolder(@NonNull View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.pname);
            orderid = itemView.findViewById(R.id.orderid);
            orderdate = itemView.findViewById(R.id.orderdate);
            paymentst = itemView.findViewById(R.id.payustatus);
            deliveryst = itemView.findViewById(R.id.status);
        }
    }
}
