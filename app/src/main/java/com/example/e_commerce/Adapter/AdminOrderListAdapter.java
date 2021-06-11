package com.example.e_commerce.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Model.OrderModel;
import com.example.e_commerce.OrderDetailsActivity;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdminOrderListAdapter extends FirestoreRecyclerAdapter<OrderModel,AdminOrderListAdapter.ListViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminOrderListAdapter(@NonNull FirestoreRecyclerOptions<OrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListViewHolder holder, int position, @NonNull OrderModel model) {
        holder.useremail.setText(model.getUserEmail());
        holder.pname.setText(model.getProductName());
        holder.odate.setText(model.getDatetime());
        holder.oid.setText(model.getOrderID());
        holder.payst.setText(model.getPayment_Status());
        holder.delist.setText(model.getDelivery_Status());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), OrderDetailsActivity.class);
                i.putExtra("username",model.getUserEmail());
                i.putExtra("productname",model.getProductName());
                i.putExtra("productimage",model.getProduct_Image());
                i.putExtra("productprice",model.getProduct_Price());
                i.putExtra("quntity",model.getQuantity());
                i.putExtra("totalprice",model.getTotal_Price());
                i.putExtra("orderdate",model.getDatetime());
                i.putExtra("paymentstatus",model.getPayment_Status());
                i.putExtra("orderid",model.getOrderID());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminordershow,parent,false);
        return new ListViewHolder(view);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView useremail,pname,oid,odate,payst,delist;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            useremail = itemView.findViewById(R.id.useremail);
            pname = itemView.findViewById(R.id.pname);
            oid = itemView.findViewById(R.id.orderid);
            odate = itemView.findViewById(R.id.orderdate);
            payst = itemView.findViewById(R.id.payustatus);
            delist = itemView.findViewById(R.id.status);
        }
    }
}
