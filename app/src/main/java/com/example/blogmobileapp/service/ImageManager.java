package com.example.blogmobileapp.service;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.blogmobileapp.R;

public class ImageManager {
    public static void loadImage(Activity activity, ImageView imageView, String imageUrl) {
        Log.d("Glide", "Load image: " + imageUrl + " From: " + activity.toString());

        GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN")
                .build());

        Glide.with(activity)
                .load(glideUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Load failed: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        Log.d("Glide", "Image loaded successfully");
                        return false;
                    }
                })
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.user_default_avatar)
                .error(R.drawable.user_default_avatar)
                .circleCrop()
                .into(imageView);
    }
}
