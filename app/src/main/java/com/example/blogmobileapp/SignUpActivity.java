package com.example.blogmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogmobileapp.service.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends AppCompatActivity {
    private EditText signupPassword, signupConfirmPassword, signupEmail;
    private Button signupBtn;
    private TextView loginAccount;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initWidgets();

        // Go to Login Activity
        loginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String confirmPassword = signupConfirmPassword.getText().toString().trim();

                // TO-DO: Add condition to check
                // Check conditions
                if (email.isEmpty()) {
                    signupEmail.setError("Email is required");
                } else if (password.isEmpty()) {
                    signupPassword.setError("Password is required");
                } else if (confirmPassword.isEmpty()) {
                    signupConfirmPassword.setError("Please confirm password");
                } else if (password.length() < 6) {
                    Toast.makeText(SignUpActivity.this, "Password length must be greater than 6", Toast.LENGTH_SHORT).show();
                } else {
                    signUpAccount(email, password);
                }
            }
        });
    }

    private void signUpAccount(String email, String password) {
        progressDialog.setTitle("Please wait..."); // TO-DO: Change Text
        progressDialog.show();

        FirebaseManager.getInstance().getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                            Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
                        } else {
                            Toast.makeText(SignUpActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() { // Debug error
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
                    }
                });
    }

    private void initWidgets() {
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirmPassword = findViewById(R.id.signup_confirm_password);
        signupBtn = findViewById(R.id.btn_signup);
        loginAccount = findViewById(R.id.signup_login_account);
        progressDialog = new ProgressDialog(this);
    }
}