package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * *  created by RexHuang
 * *  on 2020/6/29
 */
public class RTextView extends TextView {
    public RTextView(Context context) {
        super(context);
    }

    public RTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCharText(Character character){
        setText(String.valueOf(character));
    }
}
