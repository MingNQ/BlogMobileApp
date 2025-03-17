package com.example.blogmobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.blogmobileapp.common.TextStyle;
import com.example.blogmobileapp.service.NavbarManager;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity {
    private ImageView uploadImage, boldStyle, underlineStyle, italicStyle, imageAfterUpload;
    private EditText postTitle, postContent;
    private Button btnDraftSave, btnContinueUpload;

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

    // Handle action of buttons
    private void handleButtonAction() {
        // Draft saving
        btnDraftSave.setOnClickListener(view -> {
            // TO-DO: Draft save
        });

        // Continue
        btnContinueUpload.setOnClickListener(view -> {
            showUploadDialog();
        });
    }

    // Show dialog upload complete
    private void showUploadDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_upload);
        dialog.setCancelable(true);

        Button btnUploadImage, btnBack, btnCreate;
        btnUploadImage = dialog.findViewById(R.id.buttonUploadImage);
        btnBack = dialog.findViewById(R.id.buttonBack);
        btnCreate = dialog.findViewById(R.id.buttonCreate);

        imageAfterUpload = dialog.findViewById(R.id.imageAfterUpload);

        Spinner spinner = dialog.findViewById(R.id.category);
        Category[] categories = Category.values();
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
        btnUploadImage.setOnClickListener(view -> {
            uploadFromGallery();
        });

        // Back
        btnBack.setOnClickListener(view -> {
            dialog.dismiss();
        });

        // Create
        btnCreate.setOnClickListener(view -> {
            // TO-DO: Create post
        });

        dialog.show();
    }

    // Handle toolbar
    private void handleToolBar() {
        // Upload image
        uploadImage.setOnClickListener(view -> {
            uploadFromGallery();
        });

        // Change text style
        // Bold
        boldStyle.setOnClickListener(view -> {
            applyStyle(TextStyle.BOLD);
        });

        // Underline
        underlineStyle.setOnClickListener(view -> {
            applyStyle(TextStyle.UNDERLINE);
        });

        // Italic
        italicStyle.setOnClickListener(view -> {
            applyStyle(TextStyle.ITALIC);
        });
    }

    // Handle: Open gallery and upload image
    private void uploadFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, AppConstant.PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstant.PICK_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectImageUri);
                imageAfterUpload.setImageBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // TO-DO: Handle to apply text style
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

    // Initialize widgets
    private void initWidgets() {
        uploadImage = findViewById(R.id.uploadImage);
        boldStyle = findViewById(R.id.boldStyle);
        underlineStyle = findViewById(R.id.underlineStyle);
        italicStyle = findViewById(R.id.italicStyle);
        postTitle = findViewById(R.id.postUploadTitle);
        postContent = findViewById(R.id.postUploadContent);
        btnDraftSave = findViewById(R.id.buttonDraftSave);
        btnContinueUpload = findViewById(R.id.buttonContinueUpload);
    }
}