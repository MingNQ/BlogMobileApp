package com.example.blogmobileapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.R;
import com.example.blogmobileapp.common.AppConstant;
import com.example.blogmobileapp.model.PostModel;
import com.example.blogmobileapp.service.ImageManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeaturedPostAdapter extends RecyclerView.Adapter<FeaturedPostAdapter.FeaturedPostViewHolder> {
    private List<PostModel> postList;
    private Context context;

    public FeaturedPostAdapter(List<PostModel> postList) {
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
    public FeaturedPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_post, parent, false);
        return new FeaturedPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedPostViewHolder holder, int position) {
        PostModel post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.authorName.setText(post.getAuthor());
        holder.timeReading.setText(post.getTimeReading());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String date = sdf.format(new Date(post.getTimestamp()));
        holder.date.setText(date);

        if (post.getAuthorAvtResId() != null) {
            ImageManager.loadImage((Activity) context, holder.authorAvatar, post.getAuthorAvtResId());
        } else {
            ImageManager.loadImage((Activity) context, holder.authorAvatar, AppConstant.DEFAULT_PHOTO_URL);
        }

        if (post.getThumbnailResId() != null) {
            ImageManager.loadImage((Activity) context, holder.thumbnail, post.getThumbnailResId());
        } else {
            holder.thumbnail.setImageResource(R.drawable.red_heart);
        }
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
