package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dev.rexhuang.rlib.log.RLog;
import com.dev.rexhuang.rui.R;

/**
 * *  created by RexHuang
 * *  on 2020/6/15
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BasicView extends View {

    public BasicView(Context context) {
        this(context, null);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(10, 10, 100, 100);
        canvas.drawRect(rectF, paint);
        paint.setColor(Color.GREEN);
        canvas.drawOval(rectF, paint);
    }
}
