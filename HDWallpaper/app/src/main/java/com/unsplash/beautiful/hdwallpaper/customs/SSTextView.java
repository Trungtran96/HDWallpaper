package com.unsplash.beautiful.hdwallpaper.customs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Trung Tran Thanh on 10/4/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SSTextView extends TextView {

    public SSTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public SSTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public SSTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("avo.TTF", context);
        setTypeface(customFont);
    }
}
