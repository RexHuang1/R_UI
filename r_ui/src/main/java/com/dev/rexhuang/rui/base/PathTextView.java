package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.R;

/**
 * *  created by RexHuang
 * *  on 2020/6/15
 */
public class PathTextView extends View {

    private Paint mPaint;

    public PathTextView(Context context) {
        this(context, null);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.addRect(100, 100, 300, 300, Path.Direction.CW);
        path.addCircle(300, 300, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(path, mPaint);
    }
}
