package com.example.e_commerce.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.e_commerce.MainActivity;
import com.example.e_commerce.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DELAY = 3000;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setBackgroundDrawable(null);
        initializeView();
        animateLogo();
        goToMaiActivity();
    }

    private void goToMaiActivity() {
        new Handler().postDelayed(()->{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, SPLASH_DELAY);
    }

    private void animateLogo() {
        Animation fadingInAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadingInAnimation.setDuration(SPLASH_DELAY);
        imageView1.startAnimation(fadingInAnimation);
    }

    private void initializeView() {
        imageView1 = findViewById(R.id.imageView);

    }
}