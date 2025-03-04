package com.example.blogmobileapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText editTextEmail, editTextCode;
    Button btnSend, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initWidgets();

        // Setup action bar to back home
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quay lại");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    // Initialize Widgets
    private void initWidgets() {
        editTextEmail = findViewById(R.id.forget_email);
        editTextCode = findViewById(R.id.forget_code);
        btnSend = findViewById(R.id.btn_send);
        btnConfirm = findViewById(R.id.btn_confirm);
    }
}