package com.example.aplic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplic.R;
import com.example.aplic.activities.GameScreenActivity;

public class LevelSelectActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvDisplay;
    ImageButton ibBack, ibL1, ibL2, ibL3, ibL4, ibL5, ibL6, ibL7, ibL8, ibL9, ibBoss, ibBackArrow, ibForwardArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        tvDisplay = findViewById(R.id.tvLevelDisplay); ibBack = findViewById(R.id.ibBack);
        ibL1 = findViewById(R.id.ibLevel1); ibL2 = findViewById(R.id.ibLevel2); ibL3 = findViewById(R.id.ibLevel3);
        ibL4 = findViewById(R.id.ibLevel4); ibL5 = findViewById(R.id.ibLevel5); ibL6 = findViewById(R.id.ibLevel6);
        ibL7 = findViewById(R.id.ibLevel7); ibL8 = findViewById(R.id.ibLevel8); ibL9 = findViewById(R.id.ibLevel9);
        ibBoss = findViewById(R.id.ibBoss); ibBackArrow = findViewById(R.id.ibBackArrow); ibForwardArrow = findViewById(R.id.ibForwardArrow);

        ibBack.setOnClickListener(this); ibBoss.setOnClickListener(this); ibBackArrow.setOnClickListener(this); ibForwardArrow.setOnClickListener(this);
        ibL1.setOnClickListener(this); ibL2.setOnClickListener(this); ibL3.setOnClickListener(this);
        ibL4.setOnClickListener(this); ibL5.setOnClickListener(this); ibL6.setOnClickListener(this);
        ibL7.setOnClickListener(this); ibL8.setOnClickListener(this); ibL9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (ibL1.equals(view)) {
            startActivity(new Intent(view.getContext(), GameScreenActivity.class));
        } else if (ibL2.equals(view)) {
        } else if (ibL3.equals(view)) {
        } else if (ibL4.equals(view)) {
        } else if (ibL5.equals(view)) {
        } else if (ibL6.equals(view)) {
        } else if (ibL7.equals(view)) {
        } else if (ibL8.equals(view)) {
        } else if (ibL9.equals(view)) {
//        } else if (ibBack.equals(view)) { startActivity(new Intent(view.getContext(), HubActivity.class)); finish();
        } else if (ibBack.equals(view)) { finish();
        } else if (ibBoss.equals(view)) {
        } else if (ibBackArrow.equals(view)) {
        } else if (ibForwardArrow.equals(view)) {
        }
    }
}