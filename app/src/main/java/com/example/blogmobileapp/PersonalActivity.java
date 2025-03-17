package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.blogmobileapp.adapter.PostAdapter;
import com.example.blogmobileapp.model.Post;
import com.example.blogmobileapp.model.User;
import com.example.blogmobileapp.service.ImageManager;
import com.example.blogmobileapp.service.NavbarManager;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {
    TextView userName, fullName, numPosts, numFollowers, numFollowings;
    ImageView userAvatar;
    Button btnEditPersonal, btnPosts, btnSavedPosts;
    RecyclerView recyclerPersonalPosts;
    List<Post> personalPosts;
    ScrollView scrollViewPersonalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // Initialize widgets
        initWidgets();

        // Handle Navbar
        NavbarManager.setupNavbar(this, findViewById(R.id.navbar));

        // Reload User
        reloadUser();

        // Load posts
        loadPosts();
    }

    // Reload User
    private void reloadUser() {
        User user = NavbarManager._User;

        userName.setText(user.getUsername());
        fullName.setText(user.getFullname());
        ImageManager.loadImage(this, userAvatar, user.getPhotoUrl());
    }

    // Load posts
    private void loadPosts() {
        personalPosts.clear();

        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        personalPosts.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));

        PostAdapter adapter = new PostAdapter(personalPosts);
        adapter.setContext(PersonalActivity.this);

        recyclerPersonalPosts.setLayoutManager(new LinearLayoutManager(this));
        recyclerPersonalPosts.setAdapter(adapter);
    }

    // Initialize widgets
    private void initWidgets() {
        userName = findViewById(R.id.userName);
        fullName = findViewById(R.id.fullName);
        numPosts = findViewById(R.id.numPosts);
        numFollowers = findViewById(R.id.numFollowers);
        numFollowings = findViewById(R.id.numFollowings);
        userAvatar = findViewById(R.id.mainUserAvatar);
        btnEditPersonal = findViewById(R.id.buttonEditPersonal);
        btnPosts = findViewById(R.id.btnPosts);
        btnSavedPosts = findViewById(R.id.btnSavedPosts);
        recyclerPersonalPosts = findViewById(R.id.recyclerPersonalPosts);
        recyclerPersonalPosts.setFocusable(false);
        scrollViewPersonalPage = findViewById(R.id.scrollViewPersonal);

        personalPosts = new ArrayList<>();
    }
}