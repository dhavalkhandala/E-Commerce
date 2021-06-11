package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnlogin,btnregi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findid();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginintent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginintent);
            }
        });
        btnregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regintent = new Intent(MainActivity.this,signupActivity.class);
                startActivity(regintent);
            }
        });
    }

    private void getUser() {
        SharedPreferences pref = getSharedPreferences("User",MODE_PRIVATE);
        String username = pref.getString("email", null);
        String password = pref.getString("pass", null);

        if (username != null || password != null) {
            Intent intent = new Intent(this, DasboardActivity.class);
            startActivity(intent);
            Toast.makeText(this, "remember me", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void findid() {
        btnlogin = (Button)findViewById(R.id.login);
        btnregi = (Button)findViewById(R.id.signup);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getUser();
    }
}