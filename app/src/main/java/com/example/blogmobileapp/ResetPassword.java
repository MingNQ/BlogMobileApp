package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPassword extends AppCompatActivity {
    TextView textViewUsername;
    EditText editTextPassword, editTextConfirmPassword;
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initWidgets();
    }

    // Initialize Widgets
    private void initWidgets() {
        textViewUsername = findViewById(R.id.txt_username);
        editTextPassword = findViewById(R.id.reset_password);
        editTextConfirmPassword = findViewById(R.id.reset_confirm_password);
        btnResetPassword = findViewById(R.id.btn_reset_password);
    }
}