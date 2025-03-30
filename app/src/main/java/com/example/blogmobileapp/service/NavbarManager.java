package com.example.blogmobileapp.service;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.blogmobileapp.HomeActivity;
import com.example.blogmobileapp.MainActivity;
import com.example.blogmobileapp.PersonalActivity;
import com.example.blogmobileapp.R;
import com.example.blogmobileapp.SearchActivity;
import com.example.blogmobileapp.UploadActivity;
import com.example.blogmobileapp.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;

public class NavbarManager {
    private static ImageView home, search, upload, notification, userAvatar;
    private static View navbarView;
    public static UserModel _User;

    public static void setupNavbar(Activity activity, View navbar) {
        home = navbar.findViewById(R.id.homeIcon);
        upload = navbar.findViewById(R.id.uploadIcon);
        search = navbar.findViewById(R.id.searchIcon);
        notification = navbar.findViewById(R.id.notificationIcon);
        userAvatar = navbar.findViewById(R.id.userAvatar);
        navbarView = navbar;

        loadUserAvatar(activity);

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

        if (search != null) {
            search.setOnClickListener(view -> {
                Intent intent = new Intent(activity, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(intent);
            });
        }

        if (userAvatar != null) {
            userAvatar.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(activity, view);
                popupMenu.getMenuInflater().inflate(R.menu.dropdown_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.menu_view_profile) {
                        if (activity instanceof PersonalActivity) {
                            ScrollView scrollView = activity.findViewById(R.id.scrollViewPersonal);
                            scrollView.smoothScrollTo(0, 0);

                            return false;
                        }

                        Intent intent = new Intent(activity, PersonalActivity.class);
                        intent.putExtra("USER_NAME", _User.getUsername());
                        intent.putExtra("FULL_NAME", _User.getFullname());
                        intent.putExtra("USER_EMAIL", _User.getEmail());
                        intent.putExtra("USER_PHOTO", _User.getPhotoUrl());

                        activity.startActivity(intent);
                        return true;
                    } else if (item.getItemId() == R.id.menu_log_out) {
                        FirebaseAuth firebaseAuth = FirebaseManager.getInstance().getFirebaseAuth();
                        firebaseAuth.signOut();
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();

                        return true;
                    }

                    return false;
                });

                popupMenu.show();
            });
        }

        // TO-DO: Notification
    }

    public static void loadUserAvatar(Activity activity) {
        if (userAvatar != null && _User.getPhotoUrl() != null) {
            ImageManager.loadImage(activity, userAvatar, _User.getPhotoUrl());
        }
    }

    public static void reloadUser(UserModel user) {
        _User = user;
    }
}
