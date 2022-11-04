package com.example.aplic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aplic.R;
import com.example.aplic.activities.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivSplash;
    Animation splashAnim;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivSplash = findViewById(R.id.SplashScreenImage);
        splashAnim = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        ivSplash.startAnimation(splashAnim);
        intent = new Intent(getApplicationContext(), LoginActivity.class);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {startActivity(intent); finish();}, 3300);
    }
}