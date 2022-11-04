package com.example.aplic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplic.R;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etMail, etPass;
    ImageButton btChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        etName = findViewById(R.id.etForgotName); etMail = findViewById(R.id.etForgotMail); etPass = findViewById(R.id.etForgotPass);
        btChange = findViewById(R.id.btChange);
        btChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btChange)
        {
            Intent forgotIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivity(forgotIntent);
            finish();
        }
    }
}