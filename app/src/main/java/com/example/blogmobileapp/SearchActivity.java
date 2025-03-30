package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogmobileapp.adapter.SearchUserAdapter;
import com.example.blogmobileapp.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    TextView recent, clearAll;
    ImageView imgBack, imgSearch;
    EditText keyWord;
    RecyclerView recyclerSearchUser;
    List<UserModel> users = new ArrayList<>();
    SearchUserAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize widgets
        initWidgets();

        // Initialize recycler
        initRecycler();

        // Handle logic
        imgBack.setOnClickListener(view -> {
            startActivity(new Intent(SearchActivity.this, HomeActivity.class));
            finish();
        });

        clearAll.setOnClickListener(view -> {
            users.clear();
            adapter.notifyDataSetChanged();
        });
    }

    // Initialize recycler
    private void initRecycler() {
        // TO-DO: Get Data
        users.add(new UserModel("MingNQ", "Nguyen Quoc Minh", R.drawable.red_heart));
        users.add(new UserModel("MingNQ", "Nguyen Quoc Minh", R.drawable.red_heart));
        users.add(new UserModel("MingNQ", "Nguyen Quoc Minh", R.drawable.red_heart));
        users.add(new UserModel("MingNQ", "Nguyen Quoc Minh", R.drawable.red_heart));

        adapter = new SearchUserAdapter(users);
        recyclerSearchUser.setLayoutManager(new LinearLayoutManager(this));
        recyclerSearchUser.setAdapter(adapter);
    }

    // Initialize widgets
    private void initWidgets() {
        recent = findViewById(R.id.recent);
        clearAll = findViewById(R.id.clearAll);
        imgBack = findViewById(R.id.imageViewBack);
        imgSearch = findViewById(R.id.imageViewSearch);
        keyWord = findViewById(R.id.searchKeyWord);
        recyclerSearchUser = findViewById(R.id.recyclerSearchUser);
    }
}