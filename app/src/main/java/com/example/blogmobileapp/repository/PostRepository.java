package com.example.blogmobileapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blogmobileapp.model.PostModel;
import com.example.blogmobileapp.service.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private static PostRepository instance;
    private final DatabaseReference postRef;
    private final List<PostModel> postModelList = new ArrayList<>();

    private PostRepository() {
        postRef = FirebaseManager.getInstance().getFirebaseDatabase().getReference("Posts");
    }

    public static synchronized PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }

        return instance;
    }

    public LiveData<List<PostModel>> getPostListByUser(String userId) {
        MutableLiveData<List<PostModel>> postsLiveData = new MutableLiveData<>();
        Query query = postRef.orderByChild("author").equalTo(userId);

        postRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                Log.d("FIREBASE", "Dữ liệu: " + snapshot.getValue());
            } else {
                Log.e("FIREBASE", "Lỗi lấy dữ liệu: " + task.getException());
            }
        });

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PostModel> posts = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    PostModel post = postSnapshot.getValue(PostModel.class);
                    if (post != null) {
                        posts.add(post);
                    }
                }

                postsLiveData.postValue(posts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                postsLiveData.postValue(null);
            }
        });

        return postsLiveData;
    }
}