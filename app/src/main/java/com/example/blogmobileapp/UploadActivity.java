package com.example.blogmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.blogmobileapp.common.TextStyle;
import com.example.blogmobileapp.service.NavbarManager;

public class UploadActivity extends AppCompatActivity {
    private ImageView uploadImage, boldStyle, underlineStyle, italicStyle;
    private EditText postTitle, postContent;
    private Button btnDraftSave, btnContinueUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Navbar handle
        NavbarManager.setupNavbar(this);

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

        });

        // Continue
        btnContinueUpload.setOnClickListener(view -> {

        });
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