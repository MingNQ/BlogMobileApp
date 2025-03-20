package com.example.blogmobileapp.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseManager {
    private static FirebaseManager instance;

    private FirebaseManager() {
    }

    public static FirebaseManager getInstance() {
        if (instance == null) {
            instance = new FirebaseManager();
        }

        return instance;
    }

    public FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public FirebaseUser getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public FirebaseStorage getFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }
}
