package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddProductActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        setTitle("Add New Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_irontoy);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new AddIronToyFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_irontoy){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new AddIronToyFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new AddHandicraftFragment()).commit();
                }

                return true;
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}