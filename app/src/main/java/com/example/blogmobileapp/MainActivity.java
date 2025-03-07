package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogmobileapp.service.FirebaseManager;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText loginEmail, loginPassword;
    private TextView forgotPassword, signAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        // Go to Sign up Activity
        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        // Go to Forget password Activity
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgetPasswordActivity.class));
            }
        });

        // Sign In
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                signIn(email, password);
            }
        });
    }

    // Handle Sign In
    private void signIn(String email, String password) {
        // Debug error
        FirebaseManager.getInstance().getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                   } else {
                       Toast.makeText(MainActivity.this, "Sign In Fail!", Toast.LENGTH_SHORT).show();
                   }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
                });
    }

    // Initialize widgets
    private void initWidgets() {
        loginBtn = findViewById(R.id.btn_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        forgotPassword = findViewById(R.id.login_forgot_password);
        signAccount = findViewById(R.id.login_sign_account);
    }
}