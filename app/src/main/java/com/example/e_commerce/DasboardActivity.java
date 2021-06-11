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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DasboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        navigationView=findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView username = headerView.findViewById(R.id.usernames);
        preferences=getSharedPreferences("User",MODE_PRIVATE);
        username.setText(preferences.getString("email",""));
        sidenavigationmenu();
        bottomnavigationmenu();

    }

    private void bottomnavigationmenu() {
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_irontoy);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_irontoy){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new HomeFragment()).commit();
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new handicraftFragment()).commit();
                }

                return true;
            }
        });

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
                    case R.id.about:
                        Intent inten = new Intent(DasboardActivity.this, AboutActivity.class);
                        startActivity(inten);
                        break;

                    case R.id.mycart:
                        Intent intent = new Intent(DasboardActivity.this, OrderShowActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.feedback:
                        Intent intentfeedback = new Intent(DasboardActivity.this, UserFeedbackActivity.class);
                        startActivity(intentfeedback);
                        break;
                    case R.id.share:
                        Toast.makeText(DasboardActivity.this, "Click Share Application", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        SharedPreferences sharedPrefs = getSharedPreferences("User", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent1 = new Intent(DasboardActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        Toast.makeText(DasboardActivity.this, "Click Logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}