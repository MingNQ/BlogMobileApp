package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blogmobileapp.service.NavbarManager;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Navbar handle
        NavbarManager.setupNavbar(this);
    }
}