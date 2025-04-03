package com.example.blogmobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blogmobileapp.common.AppConstant;
import com.example.blogmobileapp.common.Category;
import com.example.blogmobileapp.common.TextFormatter;
import com.example.blogmobileapp.common.TextStyle;
import com.example.blogmobileapp.service.FirebaseManager;
import com.example.blogmobileapp.service.NavbarManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class UploadActivity extends AppCompatActivity {
    private ImageView boldStyle;
    private ImageView underlineStyle;
    private ImageView italicStyle;
    private EditText postTitle;
    private EditText postContent;
    private Button btnDraftSave;
    private Button btnContinueUpload;
    private Spinner spinner;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Navbar handle
        NavbarManager.setupNavbar(this, findViewById(R.id.navbar));

        // Initialize widgets
        initWidgets();

        // TO-DO: Coding logic handle
        // Handle toolbar
        handleToolBar();

        // Handle action of buttons
        handleButtonAction();
    }

    // Initialize widgets
    private void initWidgets() {
        boldStyle = findViewById(R.id.boldStyle);
        underlineStyle = findViewById(R.id.underlineStyle);
        italicStyle = findViewById(R.id.italicStyle);
        postTitle = findViewById(R.id.postUploadTitle);
        postContent = findViewById(R.id.postUploadContent);
        btnDraftSave = findViewById(R.id.buttonDraftSave);
        btnContinueUpload = findViewById(R.id.buttonContinueUpload);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_upload);
        dialog.setCancelable(true);
        spinner = dialog.findViewById(R.id.category);
    }

    // Handle action of buttons
    private void handleButtonAction() {
        // Draft saving
        btnDraftSave.setOnClickListener(view -> {
            // TO-DO: Draft save
        });

        // Continue
        btnContinueUpload.setOnClickListener(view -> showUploadDialog());
    }

    // Show dialog upload complete
    private void showUploadDialog() {
        Button btnUploadImage, btnBack, btnCreate;
        btnUploadImage = dialog.findViewById(R.id.buttonUploadImage);
        btnBack = dialog.findViewById(R.id.buttonBack);
        btnCreate = dialog.findViewById(R.id.buttonCreate);
        ImageView imageAfterUpload = dialog.findViewById(R.id.imageAfterUpload);

        String[] categoryDisplayName = {
//                getString(R.string.E10),
                getString(R.string.C00),
                getString(R.string.C01),
                getString(R.string.C02),
                getString(R.string.C03),
                getString(R.string.C04),
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryDisplayName);
        spinner.setAdapter(adapter);

        // Upload image
        btnUploadImage.setOnClickListener(view -> uploadFromGallery());

        // Back
        btnBack.setOnClickListener(view -> dialog.dismiss());

        // Create
        btnCreate.setOnClickListener(view -> uploadPost());

        dialog.show();
    }

    // Upload Post
    private void uploadPost() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng bài...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String title = postTitle.getText().toString().trim();
        String category = spinner.getSelectedItem().toString();
        SpannableStringBuilder contentBuilder = new SpannableStringBuilder(postContent.getText());

        if (title.isEmpty() || contentBuilder.toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tiêu đề và nội dung!", Toast.LENGTH_SHORT).show();
        }

        DatabaseReference databaseReference = FirebaseManager.getInstance().getFirebaseDatabase().getReference("Posts");
        String postId = databaseReference.push().getKey();

        FirebaseAuth auth = FirebaseManager.getInstance().getFirebaseAuth();
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "Anonymous";
        long timestamp = System.currentTimeMillis();

        HashMap<String, Object> postMap = new HashMap<>();
        postMap.put("category", category);
        postMap.put("title", title);
        postMap.put("author", userId);
        postMap.put("timestamp", timestamp);
        postMap.put("likes", 0);

        String formattedContent = TextFormatter.convertToCustomSyntax(contentBuilder);
        postMap.put("content", formattedContent);

        databaseReference.child(postId).setValue(postMap)
                .addOnSuccessListener(aVoid -> {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Bài viết đã đăng", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Lỗi khi đăng bài", Toast.LENGTH_SHORT).show();
                });
    }

    // Handle toolbar
    private void handleToolBar() {
        // Change text style
        // Bold
        boldStyle.setOnClickListener(view -> applyStyle(TextStyle.BOLD));

        // Underline
        underlineStyle.setOnClickListener(view -> applyStyle(TextStyle.UNDERLINE));

        // Italic
        italicStyle.setOnClickListener(view -> applyStyle(TextStyle.ITALIC));
    }

    // Handle: Open gallery and upload image
    private void uploadFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, AppConstant.PICK_IMAGE_CODE);
    }

    // Handle to apply text style
    private void applyStyle(TextStyle textStyle) {
        int start = postContent.getSelectionStart();
        int end = postContent.getSelectionEnd();

        Object o = null;

        switch (textStyle) {
            case BOLD:
                o = new StyleSpan(Typeface.BOLD);
                break;
            case ITALIC:
                o = new StyleSpan(Typeface.ITALIC);
                break;
            case UNDERLINE:
                o = new UnderlineSpan();
                break;
        }

        if (start < end) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(postContent.getText());
            spannable.setSpan(o, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            postContent.setText(spannable);
            postContent.setSelection(end);
        }
    }
}