package com.example.blogmobileapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blogmobileapp.model.UserModel;
import com.example.blogmobileapp.service.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserRepository {
    private static UserRepository instance;
    private final DatabaseReference userRef;

    private UserRepository() {
        userRef = FirebaseManager.getInstance().getFirebaseDatabase().getReference("Users");
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public LiveData<UserModel> getUserById(String userId) {
        MutableLiveData<UserModel> userLiveData = new MutableLiveData<>();
        userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel user = snapshot.getValue(UserModel.class);
                userLiveData.postValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userLiveData.postValue(null);
            }
        });

        return userLiveData;
    }
}
