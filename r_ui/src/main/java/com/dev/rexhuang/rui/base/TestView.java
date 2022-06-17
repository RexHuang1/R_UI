package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.R;

/**
 * *  created by RexHuang
 * *  on 2020/8/24
 */
public class TestView extends View {
    private String mText;
    private int mTextSize;
    private TextPaint mPaint;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestView);
        mText = typedArray.getString(R.styleable.TestView_android_text);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TestView_android_textSize, 24);
        typedArray.recycle();

        mPaint = new TextPaint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        int resultW = widthSize, resultH = heightSize;
        int contentW, contentH;
        if (widthMode == MeasureSpec.AT_MOST) {
            if (!TextUtils.isEmpty(mText)) {
                contentW = (int) mPaint.measureText(mText);
                contentW += getPaddingLeft() + getPaddingRight();
                resultW = Math.min(widthSize, contentW);
            }
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            if (!TextUtils.isEmpty(mText)) {
                contentH = mTextSize;
                contentH += getPaddingTop() + getPaddingBottom();
                resultH = Math.min(contentH, heightSize);
            }
        }

        setMeasuredDimension(resultW,resultH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = getPaddingLeft() + (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        int cy = getPaddingTop() + (getHeight() - getPaddingTop() - getPaddingBottom()) / 2;

        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        cy += metrics.descent;

        canvas.drawColor(Color.GREEN);
        if (TextUtils.isEmpty(mText)) {
            return;
        }
        canvas.drawText(mText, cx, cy, mPaint);
    }
}
