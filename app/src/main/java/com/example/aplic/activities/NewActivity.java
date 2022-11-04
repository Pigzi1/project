package com.example.aplic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplic.R;
import com.example.aplic.activities.LoginActivity;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etMail, etPass;
    ImageButton btNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        etName = findViewById(R.id.etNewName); etMail = findViewById(R.id.etNewMail); etPass = findViewById(R.id.etNewPass);
        btNew = findViewById(R.id.btNew);
        btNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btNew)
        {
            Intent forgotIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivity(forgotIntent);
            finish();
        }
    }
}