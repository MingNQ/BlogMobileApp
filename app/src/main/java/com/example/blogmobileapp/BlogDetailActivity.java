package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogmobileapp.common.TextFormatter;
import com.example.blogmobileapp.service.NavbarManager;

import java.util.ArrayList;

public class BlogDetailActivity extends AppCompatActivity {
    private TextView postCreateDate, postTitle, authorName, postContent;
//    private EditText commentContent;
//    private Button btnFollow;
//    private ImageView imgSend;
    private ImageView authorAvatar;
//    private RecyclerView recyclerViewMorePost, recyclerViewComment;
//    private List<PostModel> morePostList;
//    private List<CommentModel> commentModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        // Initialize widgets
        initWidgets();

        // Navbar handle
        NavbarManager.setupNavbar(this, findViewById(R.id.navbar));

        // Load Data
        loadDetailPost();
    }

    // Load detail content
    private void loadDetailPost() {
        Intent intent = getIntent();

        String title = intent.getStringExtra("post_title");
        String date = intent.getStringExtra("post_create_date");
        String _authorName = intent.getStringExtra("author_name");
        String content = intent.getStringExtra("post_content");
        int avtResId = intent.getIntExtra("author_avatar", 0);

        postTitle.setText(title);
        postCreateDate.setText(date);
        postContent.setText(TextFormatter.parseFormattedText(content));
        authorName.setText(_authorName);
        authorAvatar.setImageResource(avtResId);
    }

    // Initialize widgets
    private void initWidgets() {
        postCreateDate = findViewById(R.id.postCreateDate);
        postTitle = findViewById(R.id.postTitle);
        postContent = findViewById(R.id.postContent);
        authorName = findViewById(R.id.authorName);
//        commentContent = findViewById(R.id.editTextComment);
//        btnFollow =  findViewById(R.id.buttonFollow);
        authorAvatar = findViewById(R.id.authorAvatar);
    }
}