package com.example.blogmobileapp.common;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatter {
    public static String convertToCustomSyntax(SpannableStringBuilder ssb) {
        StringBuilder formattedText = new StringBuilder(ssb.toString());

        Object[] spans = ssb.getSpans(0, ssb.length(), Object.class);

        for (Object span : spans) {
            int start = ssb.getSpanStart(span);
            int end = ssb.getSpanEnd(span);

            if (span instanceof StyleSpan) {
                int style = ((StyleSpan) span).getStyle();
                if (style == Typeface.BOLD) {
                    formattedText.insert(end, "[/b]").insert(start, "[b]");
                } else if (style == Typeface.ITALIC) {
                    formattedText.insert(end, "[/i]").insert(start, "[i]");
                }
            } else if (span instanceof UnderlineSpan) {
                formattedText.insert(end, "[/u]").insert(start, "[u]");
            }
        }

        return formattedText.toString();
    }

    public static SpannableStringBuilder parseFormattedText(String text) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        applySpan(ssb, "\\[b\\](.*?)\\[/b\\]", new StyleSpan(Typeface.BOLD));
        applySpan(ssb, "\\[i\\](.*?)\\[/i\\]", new StyleSpan(Typeface.BOLD));
        applySpan(ssb, "\\[u\\](.*?)\\[/u\\]", new UnderlineSpan());

        return ssb;
    }

    private static void applySpan(SpannableStringBuilder ssb, String regex, Object span) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ssb.toString());

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            ssb.replace(start, end, matcher.group(1));
            ssb.setSpan(span, start, start + matcher.group(1).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
