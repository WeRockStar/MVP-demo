package com.werockstar.mvpgithubapi.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);

        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    public void init(Context context) {
        Typeface tf = Typeface
                .createFromAsset(context.getAssets(), "fonts/thaisans.ttf");
        setTypeface(tf);
    }
}
