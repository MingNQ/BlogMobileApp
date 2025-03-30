package com.example.blogmobileapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.R;
import com.example.blogmobileapp.model.CommentModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<CommentModel> comments;

    public CommentAdapter(List<CommentModel> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentModel comment = comments.get(position);
        holder.userAvatar.setImageResource(comment.getAvatarResId());
        holder.userName.setText(comment.getUserName());
        holder.createDate.setText(comment.getCreateDate());
        holder.commentContent.setText(comment.getCommentContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatar;
        TextView userName, createDate, commentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.userAvatar);
            userName = itemView.findViewById(R.id.userName);
            createDate = itemView.findViewById(R.id.createDate);
            commentContent = itemView.findViewById(R.id.commentContent);
        }
    }
}
