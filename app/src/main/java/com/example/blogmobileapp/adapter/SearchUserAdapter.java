package com.example.blogmobileapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.R;
import com.example.blogmobileapp.model.UserModel;

import java.util.List;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder> {
    List<UserModel> userList;

    public SearchUserAdapter(List<UserModel> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public SearchUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user, parent, false);
        return new SearchUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUserViewHolder holder, int position) {
        UserModel user = userList.get(position);
        holder.username.setText(user.getUsername());
        holder.fullname.setText(user.getFullname());
        holder.avatar.setImageResource(user.getAvatarResId());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class SearchUserViewHolder extends RecyclerView.ViewHolder {
        TextView username, fullname;
        ImageView avatar;

        public SearchUserViewHolder(@NonNull View itemView) {
            super(itemView);

            initWidgets(itemView);
        }

        private void initWidgets(@NonNull View itemView) {
            username = itemView.findViewById(R.id.searchUsername);
            fullname = itemView.findViewById(R.id.searchFullname);
            avatar = itemView.findViewById(R.id.searchUserAvatar);
        }
    }
}
