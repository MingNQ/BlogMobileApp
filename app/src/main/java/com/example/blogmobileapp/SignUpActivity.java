package com.example.blogmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogmobileapp.model.User;
import com.example.blogmobileapp.service.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

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
                finish();
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
                    String name = email.split("@")[0];
                    String photoUrl = "https://example.com/default-avatar.png";

                    signUpAccount(email, password, name, photoUrl);
                }
            }
        });
    }

    private void signUpAccount(String email, String password, String name, String photoUrl) {
        progressDialog.setTitle("Please wait..."); // TO-DO: Change Text
        progressDialog.show();

        FirebaseManager.getInstance().getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseManager.getInstance().getFirebaseUser();

                        user.sendEmailVerification().addOnCompleteListener(verifyTask -> {
                                   if (verifyTask.isSuccessful()) {
                                       Toast.makeText(this, "Email xác nhận đã được gửi. Vui lòng kiểm tra hộp thư!", Toast.LENGTH_LONG).show();

                                       String userId = user.getUid();
                                       User newUser = new User(userId, name, name, email, photoUrl);

                                       DatabaseReference userRef = FirebaseManager.getInstance().
                                               getFirebaseDatabase().getReference("Users").
                                               child(user.getUid());
                                       userRef.setValue(newUser)
                                               .addOnCompleteListener(dbTask -> {
                                                   if (dbTask.isSuccessful()) {
                                                       Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                                   } else {
                                                       Toast.makeText(this, "Lỗi lưu Database: " + dbTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                   }
                                               })
                                               .addOnFailureListener(failTask -> {
                                                   Toast.makeText(this, "Lỗi lưu Database: " + failTask.getMessage(), Toast.LENGTH_LONG).show();
                                               });

                                       FirebaseManager.getInstance().getFirebaseAuth().signOut();
                                   } else {
                                       Toast.makeText(this,
                                               "Gửi email thất bại: " + verifyTask.getException().getMessage(),
                                               Toast.LENGTH_LONG).show();
                                   }
                                });
                    } else {
                        Toast.makeText(SignUpActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
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

    // Initialize widgets
    private void initWidgets() {
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirmPassword = findViewById(R.id.signup_confirm_password);
        signupBtn = findViewById(R.id.btn_signup);
        loginAccount = findViewById(R.id.signup_login_account);
        progressDialog = new ProgressDialog(this);
    }
}