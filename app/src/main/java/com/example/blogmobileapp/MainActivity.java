package com.example.blogmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthor, tvContent, tvDate;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        loadPostData("qbD8eZasLTECYh5zGi41");
    }

    // Load Post
    private void loadPostData(String postId) {
        DocumentReference docRef = db.collection("post").document(postId);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    // Check if exist document
                    if (document.exists()) {
                        String title = document.getString("title");
                        String content = document.getString("content");
                        Timestamp createdAt = document.getTimestamp("createdAt");
                        String userId = document.getString("userId");

                        // Format date
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                        // Set data
                        tvTitle.setText(title);
                        tvContent.setText(content);
                        tvDate.setText(sdf.format(createdAt.toDate()));

                        // Load author
                        loadAuthor(userId);
                    } else { // Show if error
                        Toast.makeText(MainActivity.this, "Lỗi không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                } else { // Show if error
                    Toast.makeText(MainActivity.this, "Lỗi không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Load author
    private void loadAuthor(String userId) {
        if (userId == null) return;

        db.collection("user").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        String userId = document.getString("username");
                        tvAuthor.setText(userId);
                    } else {
                        tvAuthor.setText("Null");
                    }
                }
            }
        });
    }

    // Initial
    private void initialize() {
        // Initialize widgets
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvContent = findViewById(R.id.tvContent);
        tvDate = findViewById(R.id.tvDate);


        db = FirebaseFirestore.getInstance(); // Initialize database
    }
}