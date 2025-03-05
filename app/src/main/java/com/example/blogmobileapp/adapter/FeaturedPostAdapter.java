package com.example.blogmobileapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.R;
import com.example.blogmobileapp.model.Post;

import java.util.List;

public class FeaturedPostAdapter extends RecyclerView.Adapter<FeaturedPostAdapter.FeaturedPostViewHolder> {
    private List<Post> postList;

    public FeaturedPostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public FeaturedPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_post, parent, false);
        return new FeaturedPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedPostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.authorName.setText(post.getAuthorName());
        holder.timeReading.setText(post.getTimeReading());
        holder.date.setText(post.getDate());
        holder.thumbnail.setImageResource(post.getThumbnailResId());
        holder.authorAvatar.setImageResource(post.getAuthorAvtResId());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class FeaturedPostViewHolder extends RecyclerView.ViewHolder {
        TextView title, authorName, date, timeReading;
        ImageView thumbnail, authorAvatar;

        public FeaturedPostViewHolder(@NonNull View itemView) {
            super(itemView);
            initWidgets(itemView);
        }

        private void initWidgets(@NonNull View itemView) {
            title = itemView.findViewById(R.id.postTitle);
            authorName = itemView.findViewById(R.id.postAuthorName);
            date = itemView.findViewById(R.id.postCreateDate);
            timeReading = itemView.findViewById(R.id.postTimeReading);
            thumbnail = itemView.findViewById(R.id.postThumbnail);
            authorAvatar = itemView.findViewById(R.id.postAuthorAvatar);
        }
    }
}
