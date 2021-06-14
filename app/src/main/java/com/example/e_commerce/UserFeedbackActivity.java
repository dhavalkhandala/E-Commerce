package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Model.FeedbackModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class UserFeedbackActivity extends AppCompatActivity {

    EditText title,messsage;
    Button btnsend;
    FeedbackModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        setTitle("Send Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id = UUID.randomUUID().toString();
        title = findViewById(R.id.feedback_title);
        messsage = findViewById(R.id.feedbackmessage);
        btnsend = findViewById(R.id.send_feedback_btn);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valid = "true";
                if (title.getText().toString().equals("")) {
                    title.setError("Please Enter Product Name");
                    valid = "false";
                }
                if (messsage.getText().toString().equals("")) {
                    messsage.setError("Please Enter Product Description");
                    valid = "false";
                }
                if (valid=="true") {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String userEmail = user.getEmail();

                    model = new FeedbackModel();
                    model.setFeedbacktitle(title.getText().toString());
                    model.setMessage(messsage.getText().toString());
                    model.setUseremail(userEmail);
                    model.setId(id);

                    FirebaseFirestore.getInstance().collection("FeedBack")
                            .document(id).set(model)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(UserFeedbackActivity.this,DasboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(UserFeedbackActivity.this, "Feedback Successfully Stored", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(UserFeedbackActivity.this, "Error:"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                }
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}