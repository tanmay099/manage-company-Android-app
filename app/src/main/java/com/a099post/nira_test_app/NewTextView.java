package com.a099post.nira_test_app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Map;

/**
 * Created by DESKTOP on 18-09-2017.
 */

public class NewTextView extends android.support.v7.widget.AppCompatTextView {


    private static Map<String, Typeface> mTypefaces;

    public NewTextView(Context context) {
        super(context);
    }

    public NewTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);

    }

    public NewTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);

    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.NewTextView);
        String customFont = a.getString(R.styleable.NewTextView_customTypeface);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(ctx.getAssets(),"fonts/"+asset);
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

        setTypeface(typeface);
        return true;
    }
}
