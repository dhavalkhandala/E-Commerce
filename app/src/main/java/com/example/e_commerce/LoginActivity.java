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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button btnlogin;
    TextView linksignup;
    EditText txtemail,txtpass;
    ProgressDialog progressdialog;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btn_login);
        linksignup = findViewById(R.id.link_signup);
        txtemail = findViewById(R.id.logininput_email);
        txtpass = findViewById(R.id.logininput_password);

        progressdialog = new ProgressDialog(LoginActivity.this);
        progressdialog.setMessage("Please Wait....");

        linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linksignupintent = new Intent(LoginActivity.this,signupActivity.class);
                startActivity(linksignupintent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    progressdialog.show();
                    if (txtemail.getText().toString().equals("admin@gmail.com") && txtpass.getText().toString().equals("admin123")) {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, AdminDasboardActivity.class);
                        startActivity(intent);
                        finish();
                        progressdialog.dismiss();
                    } else {
                        progressdialog.show();
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(txtemail.getText().toString(), txtpass.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(LoginActivity.this, DasboardActivity.class);
                                            startActivity(intent);
                                            finish();
                                            progressdialog.dismiss();
                                        } else {
                                            progressdialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "Error: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
            }
        });
    }
    public boolean validate() {
        valid = true;

        String email = txtemail.getText().toString();
        String password = txtpass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtemail.setError("enter a valid email address");
            txtemail.setFocusable(true);
            valid = false;
        } else {
            valid = true;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            txtpass.setError("between 4 and 10 alphanumeric characters");
            txtpass.setFocusable(true);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}