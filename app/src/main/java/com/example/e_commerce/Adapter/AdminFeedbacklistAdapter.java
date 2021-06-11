package com.example.e_commerce.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Model.FeedbackModel;
import com.example.e_commerce.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdminFeedbacklistAdapter extends FirestoreRecyclerAdapter<FeedbackModel,AdminFeedbacklistAdapter.AdminFeedbacklistViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminFeedbacklistAdapter(@NonNull FirestoreRecyclerOptions<FeedbackModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminFeedbacklistViewHolder holder, int position, @NonNull FeedbackModel model) {
        holder.feed_title.setText("Feedback Title: "+model.getFeedbacktitle());
        holder.feed_msg.setText("Feedback Message: "+model.getMessage());
        holder.useremail.setText("Sent User: "+model.getUseremail());
    }

    @NonNull
    @Override
    public AdminFeedbacklistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminfeedbacklist,parent,false);
        return new AdminFeedbacklistViewHolder(view);
    }

    public class AdminFeedbacklistViewHolder extends RecyclerView.ViewHolder {
        TextView feed_title,feed_msg,useremail;
        public AdminFeedbacklistViewHolder(@NonNull View itemView) {
            super(itemView);
            feed_title = itemView.findViewById(R.id.title_feed);
            feed_msg = itemView.findViewById(R.id.msg_feed);
            useremail = itemView.findViewById(R.id.email);
        }
    }
}
