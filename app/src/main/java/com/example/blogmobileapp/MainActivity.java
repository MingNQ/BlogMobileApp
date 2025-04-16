package com.example.blogmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogmobileapp.model.UserModel;
import com.example.blogmobileapp.service.FirebaseManager;
import com.example.blogmobileapp.service.NavbarManager;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
        signAccount.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignUpActivity.class)));

        // Go to Forget password Activity
        forgotPassword.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ForgetPasswordActivity.class)));

        // Sign In
        loginBtn.setOnClickListener(view -> {
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();

            Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();
//            signIn(email, password);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseManager.getInstance().getFirebaseAuth().getCurrentUser();

        if (currentUser != null) {
            userReload(currentUser);
            finish();
        }
    }

    // Handle Sign In
    private void signIn(String email, String password) {
        // Debug error
        FirebaseManager.getInstance().getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       FirebaseUser user = FirebaseManager.getInstance().getFirebaseUser();
                       if (user != null && user.isEmailVerified()) {
                           userReload(user); // Reload user information
                       } else {
                           Toast.makeText(this,
                                   "Vui lòng xác nhận email trước khi đăng nhập!",
                                   Toast.LENGTH_LONG).show();
                           FirebaseManager.getInstance().getFirebaseAuth().signOut();
                       }
                   } else {
                       Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                   }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show(); // TO-DO: Change Text
                });
    }

    // Reload user from database
    private void userReload(FirebaseUser user) {
        DatabaseReference userRef = FirebaseManager.getInstance().getFirebaseDatabase().getReference("Users")
                .child(user.getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userName = snapshot.child("username").getValue(String.class);
                    String fullName = snapshot.child("fullname").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String photoUrl = snapshot.child("photoUrl").getValue(String.class);

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("USER_NAME", userName);
                    intent.putExtra("FULL_NAME", fullName);
                    intent.putExtra("USER_EMAIL", email);
                    intent.putExtra("USER_PHOTO", photoUrl);
                    NavbarManager.reloadUser(new UserModel(user.getUid(), userName, fullName, email, photoUrl));
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw new UnsupportedOperationException("Error");
            }
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