package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogmobileapp.model.CommentModel;
import com.example.blogmobileapp.model.Post;
import com.example.blogmobileapp.service.NavbarManager;

import java.util.ArrayList;
import java.util.List;

public class BlogDetailActivity extends AppCompatActivity {
    private TextView postCreateDate, postTitle, authorName;
    private EditText commentContent;
    private Button btnFollow;
    private ImageView imgSend, authorAvatar;
    private RecyclerView recyclerViewMorePost, recyclerViewComment;
    private List<Post> morePostList;
    private List<CommentModel> commentModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        // Initialize widgets
        initWidgets();

        // Navbar handle
        NavbarManager.setupNavbar(this);

        // Load Data
        loadDetailPost();
    }

    // Load detail content
    private void loadDetailPost() {
        Intent intent = getIntent();

        String title = intent.getStringExtra("post_title");
        String date = intent.getStringExtra("post_create_date");
        String _authorName = intent.getStringExtra("author_name");
        int avtResId = intent.getIntExtra("author_avatar", 0);

        postTitle.setText(title);
        postCreateDate.setText(date);
        authorName.setText(_authorName);
        authorAvatar.setImageResource(avtResId);
    }

    // Initialize widgets
    private void initWidgets() {
        postCreateDate = findViewById(R.id.postCreateDate);
        postTitle = findViewById(R.id.postTitle);
        authorName = findViewById(R.id.authorName);
        commentContent = findViewById(R.id.editTextComment);
        btnFollow =  findViewById(R.id.buttonFollow);
        imgSend = findViewById(R.id.imageViewSend);
        authorAvatar = findViewById(R.id.authorAvatar);

        recyclerViewMorePost = findViewById(R.id.recyclerMorePost);
        recyclerViewComment = findViewById(R.id.recyclerComment);

        morePostList = new ArrayList<>();
        commentModelList = new ArrayList<>();
    }
}