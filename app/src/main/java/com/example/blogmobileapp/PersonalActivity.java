package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.blogmobileapp.adapter.PostAdapter;
import com.example.blogmobileapp.model.PostModel;
import com.example.blogmobileapp.model.UserModel;
import com.example.blogmobileapp.service.FirebaseManager;
import com.example.blogmobileapp.service.ImageManager;
import com.example.blogmobileapp.service.NavbarManager;
import com.example.blogmobileapp.viewmodel.PostViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {
    TextView userName, fullName, numPosts, numFollowers, numFollowings;
    ImageView userAvatar;
    Button btnEditPersonal, btnPosts, btnSavedPosts;
    RecyclerView recyclerPersonalPosts;
    List<PostModel> personalPosts;
    ScrollView scrollViewPersonalPage;
    PostViewModel postViewModel;

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
        UserModel user = NavbarManager._User;

        userName.setText(user.getUsername());
        fullName.setText(user.getFullname());
        ImageManager.loadImage(this, userAvatar, user.getPhotoUrl());
    }

    // Load posts
    private void loadPosts() {
        personalPosts.clear();
        PostAdapter adapter = new PostAdapter(personalPosts);
        FirebaseAuth auth = FirebaseManager.getInstance().getFirebaseAuth();
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "Anonymous";

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.loadPostsByUserId(userId);
        postViewModel.getPostsLiveData().observe(this, posts -> {
            if (posts != null) {
                personalPosts = posts;
                adapter.setPosts(personalPosts);
                adapter.setContext(PersonalActivity.this);
            }
        });

        updateInformation();
        recyclerPersonalPosts.setLayoutManager(new LinearLayoutManager(this));
        recyclerPersonalPosts.setAdapter(adapter);
    }

    // Update information
    private void updateInformation() {
        numPosts.setText(String.valueOf(personalPosts.size()));
        numFollowers.setText(String.valueOf(0));
        numFollowings.setText(String.valueOf(0));
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