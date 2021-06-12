package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AdminDasboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dasboard);

        navigationView=findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView username = headerView.findViewById(R.id.usernames);
        preferences=getSharedPreferences("User",MODE_PRIVATE);
        preferences=getSharedPreferences("Admin",MODE_PRIVATE);
        username.setText(preferences.getString("adminemail",""));
        sidenavigationmenu();
        
    }
    private void sidenavigationmenu() {
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.p_history:
                        Intent payint = new Intent(AdminDasboardActivity.this, AdminPaymentHistoryActivity.class);
                        startActivity(payint);
                        break;
                    case R.id.add_product:
                        Intent intent1 = new Intent(AdminDasboardActivity.this, AddProductActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.allocation:
                        Intent orderintent = new Intent(AdminDasboardActivity.this, AdminOrderControlActivity.class);
                        startActivity(orderintent);
                        break;
                    case R.id.product_list:
                        Intent prodintent = new Intent(AdminDasboardActivity.this, AdminProductListActivity.class);
                        startActivity(prodintent);
                        break;
                    case R.id.managefeedback:
                        Intent feedintent = new Intent(AdminDasboardActivity.this, AdminFeedbackListActivity.class);
                        startActivity(feedintent);
                        Toast.makeText(AdminDasboardActivity.this, "Click Share Application", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        SharedPreferences sharedPrefs = getSharedPreferences("Admin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent2 = new Intent(AdminDasboardActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(AdminDasboardActivity.this, "Click Logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}