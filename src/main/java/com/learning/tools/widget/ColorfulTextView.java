package com.learning.tools.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.jetbrains.annotations.NotNull;

/**
 * 自定义多样式TextView
 * Create by Qing at 2024/8/20 11:31
 */
public class ColorfulTextView extends AppCompatTextView {

    public interface OnClickedListenerByPosition {
        void onClicked(View view, int position);
    }

    private Context context;

    public ColorfulTextView(Context context) {
        this(context, null);
    }

    public ColorfulTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorfulTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        setHighlightColor(Color.TRANSPARENT);
        //setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 文字，颜色，大小
     *
     * @return
     */
    public ColorfulTextView appendText(String text, int textColor, int textSize) {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dp2sp(textSize));
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(absoluteSizeSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        append(spannableString);
        return this;
    }

    /**
     * 文字，颜色，大小 设置中划线
     *
     * @return
     */
    public ColorfulTextView appendStrikeThroughText(String text, int textColor, int textSize, boolean isBold) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(text);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dp2sp(textSize));
        StyleSpan styleSpan = new StyleSpan(isBold ? Typeface.BOLD : Typeface.NORMAL);
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(absoluteSizeSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(strikethroughSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        append(spannableString);
        return this;
    }


    /**
     * 文字，颜色，大小，点击事件
     *
     * @return
     */
    public ColorfulTextView appendText(String text, int textColor, int textSize, ColorfulTextView.OnClickedListenerByPosition listener, int position) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NotNull View view) {
                listener.onClicked(view, position);
            }
            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dp2sp(textSize));
        spannableString.setSpan(absoluteSizeSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (listener != null) {
            spannableString.setSpan(clickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        append(spannableString);
        return this;
    }

    /**
     * 文字，颜色，大小，是否加粗
     *
     * @return
     */
    public ColorfulTextView appendText(String text, int textColor, int textSize, boolean isBold) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dp2sp(textSize));
        StyleSpan styleSpan = new StyleSpan(isBold ? Typeface.BOLD : Typeface.NORMAL);
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(absoluteSizeSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        append(spannableString);
        return this;
    }

    /**
     * 文字，颜色，大小，是否加粗，点击事件
     *
     * @return
     */
    public ColorfulTextView appendText(String text, int textColor, int textSize, boolean isBold, OnClickListener listener) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(dp2sp(textSize));
        StyleSpan styleSpan = new StyleSpan(isBold ? Typeface.BOLD : Typeface.NORMAL);
        spannableString.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(absoluteSizeSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (listener != null) {
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true);
                }
            }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        append(spannableString);
        return this;
    }

    public ColorfulTextView appendImage(Drawable drawable) {
        SpannableString spannableString = new SpannableString("图片");
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        append(spannableString);
        return this;
    }


    public ColorfulTextView clear() {
        setText("");
        return this;
    }

    /**
     * 添加空格
     *
     * @return
     */
    public ColorfulTextView appendSpace() {
        append(" ");
        return this;
    }

    /**
     * 添加换行
     * @return
     */
    public ColorfulTextView appendLineFeed() {
        append("\n");
        return this;
    }

    public int dp2sp(float dpVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics()));
    }
}
