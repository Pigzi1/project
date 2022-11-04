package com.example.aplic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplic.R;

public class HubActivity extends AppCompatActivity implements View.OnClickListener {

    Button btPlay, btOptions, btStatistics, btAccount, btAchievements;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        btPlay = findViewById(R.id.btPlay);
        btOptions = findViewById(R.id.btOptions);
        btStatistics = findViewById(R.id.btStatistics);
        btAccount = findViewById(R.id.btAccount);
        btAchievements = findViewById(R.id.btAchievements);

        btPlay.setOnClickListener(this);
        btOptions.setOnClickListener(this);
        btStatistics.setOnClickListener(this);
        btAccount.setOnClickListener(this);
        btAchievements.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btPlay) {
            intent = new Intent(view.getContext(), LevelSelectActivity.class);
            startActivity(intent);
        }
        else if (view == btOptions) {
            Toast.makeText(this, "options", Toast.LENGTH_SHORT).show();
        }
        else if (view == btStatistics) {
            Toast.makeText(this, "stats", Toast.LENGTH_SHORT).show();
        }
        else if (view == btAccount) {
            Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();
        }
        else if (view == btAchievements) {
            Toast.makeText(this, "achievements", Toast.LENGTH_SHORT).show();
        }
    }
}