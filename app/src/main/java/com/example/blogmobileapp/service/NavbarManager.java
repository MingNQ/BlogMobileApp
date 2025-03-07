package com.example.blogmobileapp.service;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.blogmobileapp.HomeActivity;
import com.example.blogmobileapp.R;
import com.example.blogmobileapp.UploadActivity;

public class NavbarManager {
    public static void setupNavbar(Activity activity) {
        ImageView home, search, upload, notification;

        home = activity.findViewById(R.id.homeIcon);
        upload = activity.findViewById(R.id.uploadIcon);
        search = activity.findViewById(R.id.searchIcon);
        notification = activity.findViewById(R.id.notificationIcon);

        if (home != null) {
            home.setOnClickListener(view -> {
                if (activity instanceof HomeActivity) {
                    ScrollView scrollView = activity.findViewById(R.id.homeScrollView);
                    scrollView.smoothScrollTo(0, 0);
                    return;
                }
                Intent intent = new Intent(activity, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                activity.startActivity(intent);
                activity.finish();
            });
        }

        if (upload != null) {
            upload.setOnClickListener(view -> {
                if (activity instanceof UploadActivity) {
                    return;
                }

                Intent intent = new Intent(activity, UploadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(intent);
            });
        }

        // TO-DO: Search, Notification
    }
}
