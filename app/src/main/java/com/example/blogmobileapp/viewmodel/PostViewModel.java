package com.example.blogmobileapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.blogmobileapp.model.PostModel;
import com.example.blogmobileapp.repository.PostRepository;

import java.util.List;

public class PostViewModel extends ViewModel {
    private final PostRepository postRepository;
    private LiveData<List<PostModel>> postsLiveData;

    public PostViewModel() {
        postRepository = PostRepository.getInstance();
    }

    public void loadPostsByUserId(String userId) {
        postsLiveData = postRepository.getPostListByUser(userId);
    }

    public LiveData<List<PostModel>> getPostsLiveData() {
        return postsLiveData;
    }
}
