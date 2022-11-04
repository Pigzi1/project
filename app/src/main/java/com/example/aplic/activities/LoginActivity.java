package com.example.aplic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplic.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin, btForgot, btNew;
    EditText etName, etMail, etPass;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btLogin); btForgot = findViewById(R.id.btForgot); btNew = findViewById(R.id.btNew);
        etName = findViewById(R.id.etName); etMail = findViewById(R.id.etMail); etPass = findViewById(R.id.etPass);
        btLogin.setOnClickListener(this); btNew.setOnClickListener(this); btForgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btLogin)
        {
            intent = new Intent(view.getContext(), HubActivity.class);
        }
        else if (view == btNew)
        {
            intent = new Intent(view.getContext(), NewActivity.class);
        }
        else if (view == btForgot)
        {
            intent = new Intent(view.getContext(), ForgotActivity.class);
        }
        startActivity(intent);
        finish();
    }
}