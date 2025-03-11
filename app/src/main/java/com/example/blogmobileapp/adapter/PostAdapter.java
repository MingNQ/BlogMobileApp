package com.example.blogmobileapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.BlogDetailActivity;
import com.example.blogmobileapp.R;
import com.example.blogmobileapp.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList;
    private Context context;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context _context) {
        this.context = _context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.authorName.setText(post.getAuthorName());
        holder.date.setText(post.getDate());
        holder.reactCount.setText(String.valueOf(post.getReactCount()));
        holder.commentCount.setText(String.valueOf(post.getCommentCount()));
        holder.thumbnail.setImageResource(post.getThumbnailResId());
        holder.authorAvatar.setImageResource(post.getAuthorAvtResId());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, BlogDetailActivity.class);
            intent.putExtra("post_title", post.getTitle());
            intent.putExtra("post_create_date", post.getDate());
            intent.putExtra("author_name", post.getAuthorName());
            intent.putExtra("author_avatar", post.getAuthorAvtResId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title, authorName, date, reactCount, commentCount;
        ImageView thumbnail, authorAvatar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            initWidgets(itemView);
        }

        private void initWidgets(View itemView) {
            title = itemView.findViewById(R.id.postTitle);
            authorName = itemView.findViewById(R.id.postAuthorName);
            date = itemView.findViewById(R.id.postCreateDate);
            reactCount = itemView.findViewById(R.id.postReactCount);
            commentCount = itemView.findViewById(R.id.postCommentCount);
            thumbnail = itemView.findViewById(R.id.postThumbnail);
            authorAvatar = itemView.findViewById(R.id.postAuthorAvatar);
        }
    }
}
