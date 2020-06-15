package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * *  created by RexHuang
 * *  on 2020/6/15
 */
public class RectPointView extends View {

    private int mX, mY;
    private Paint mPaint;
    private Rect mRect;

    public RectPointView(Context context) {
        this(context, null);
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(50);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect = new Rect(250, 250, 500, 600);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int) event.getX();
        mY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                mX = -1;
                mY = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRect.contains(mX, mY)) {
            mPaint.setColor(Color.RED);
        } else {
            mPaint.setColor(Color.BLACK);
        }

        canvas.drawRect(mRect, mPaint);
    }
}
