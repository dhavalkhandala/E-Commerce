package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signupActivity extends AppCompatActivity {

    TextView linklogin;
    Button btnsignup;
    UserModel model;
    ProgressDialog progressdialog;
    EditText txtuname,txtemail,txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtuname = findViewById(R.id.input_name);
        txtemail = findViewById(R.id.input_email);
        txtpass = findViewById(R.id.input_password);
        btnsignup = findViewById(R.id.btn_signup);
        linklogin = findViewById(R.id.link_login);

        progressdialog = new ProgressDialog(signupActivity.this);
        progressdialog.setMessage("Please Wait....");


        linklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkloginintent = new Intent(signupActivity.this,LoginActivity.class);
                startActivity(linkloginintent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                String valid = "true";
                if (txtuname.getText().toString().equals("")) {
                    txtuname.setError("Please Enter Valid Username");
                    progressdialog.dismiss();
                    valid = "false";
                }
                if (txtemail.getText().toString().equals("")) {
                    txtemail.setError("Please Enter Valid Email Id");
                    progressdialog.dismiss();
                    valid = "false";
                }
                if (txtpass.getText().toString().equals("")) {
                    txtpass.setError("Please Enter Valid Password");
                    valid = "false";
                    progressdialog.dismiss();
                }
                if (valid=="true") {
                    model = new UserModel(
                            txtuname.getText().toString(),
                            txtemail.getText().toString(),
                            txtpass.getText().toString());

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtemail.getText().toString(), txtpass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressdialog.dismiss();
                                        Toast.makeText(signupActivity.this, "Your Account Registered Successfully...", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(signupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        FirebaseFirestore.getInstance().collection("Users").document(txtemail.getText().toString()).set(model)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                        } else {
                                                            Toast.makeText(signupActivity.this, "Error " + task.getException().toString(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        progressdialog.dismiss();
                                        Toast.makeText(signupActivity.this, "Error" + task.getException().toString(), Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }

            }
        });
    }

}