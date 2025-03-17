package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogmobileapp.adapter.FeaturedPostAdapter;
import com.example.blogmobileapp.adapter.PostAdapter;
import com.example.blogmobileapp.model.Post;
import com.example.blogmobileapp.model.User;
import com.example.blogmobileapp.service.NavbarManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerFeaturePost, recyclerPost;
    private FeaturedPostAdapter featuredPostAdapter;
    private List<Post> featuredPostList, otherPostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Widgets
        initWidgets();

        // Navbar handle
        View navbar = findViewById(R.id.navbar);
        NavbarManager.setupNavbar(this, navbar);

        // Featured Post
        generateFeaturedPost();

        // Category
        generateCategory();

        // Post
        generatePost();
    }

    // Initialize Widgets
    private void initWidgets() {
        recyclerFeaturePost = findViewById(R.id.recyclerFeaturedPost);
        recyclerFeaturePost.setFocusable(false);
        recyclerPost = findViewById(R.id.recyclerPost);
        recyclerPost.setFocusable(false);
    }

    // Other Posts
    private void generatePost() {
        recyclerPost.setLayoutManager(new LinearLayoutManager(this));

        otherPostList = new ArrayList<>();
        otherPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        otherPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        otherPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));
        otherPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 10, 10));

        PostAdapter postAdapter = new PostAdapter(otherPostList);
        postAdapter.setContext(HomeActivity.this);

        recyclerPost.setAdapter(postAdapter);
    }

    // Featured Post
    private void generateFeaturedPost() {
        recyclerFeaturePost.setLayoutManager(new LinearLayoutManager(this));

        featuredPostList = new ArrayList<>();
        featuredPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 0, 0));
        featuredPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 0, 0));
        featuredPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 0, 0));
        featuredPostList.add(new Post("Điều duy nhất có ý nghĩa trong cuộc đời", "LongNguyen", "23/1/2025", "4 phut doc", R.drawable.red_heart, R.drawable.red_heart, 0, 0));

        featuredPostAdapter = new FeaturedPostAdapter(featuredPostList);
        recyclerFeaturePost.setAdapter(featuredPostAdapter);
    }

    // Category
    private void generateCategory() {
        Flow flow = findViewById(R.id.flowLayout);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        List<Integer> textViewIds = new ArrayList<>();

        String[] listData = { "Khoa học - Công nghệ", "Quan điểm - Tranh luận", "Tài chính", "Thể thao" };

        for (String text : listData) {
            TextView textView = new TextView(this);
            textView.setId(View.generateViewId());
            textView.setText(text);
            textView.setPadding(30, 20, 30, 20);
            textView.setTextSize(14);
            textView.setTextColor(ContextCompat.getColor(this, R.color.black));
            textView.setBackgroundResource(R.drawable.circle_border);

            constraintLayout.addView(textView);
            textViewIds.add(textView.getId());
        }

        int[] idsArr = new int[textViewIds.size()];
        for (int i = 0; i < textViewIds.size(); i++) {
            idsArr[i] = textViewIds.get(i);
        }
        flow.setReferencedIds(idsArr);
    }
}