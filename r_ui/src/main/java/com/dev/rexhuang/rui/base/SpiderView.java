package com.dev.rexhuang.rui.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * *  created by RexHuang
 * *  on 2020/6/15
 */
public class SpiderView extends View {

    private Paint radarPaint, valuePaint, skillPaint;
    private float radius;
    private int centerX, centerY;
    private int count = 6;
    // 计算出每个夹角的度数
    private float angle = (float) (Math.PI * 2 / count);
    private double[] values = {2, 5, 1, 6, 4, 5};
    private String[] skill = {"攻击", "法强", "护甲", "魔抗", "攻速", "移速"};
    private float maxValue = 6;

    public SpiderView(Context context) {
        this(context, null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setColor(Color.RED);

        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setColor(Color.BLUE);

        skillPaint = new Paint();
        skillPaint.setTextSize(40);
        skillPaint.setColor(Color.RED);
        skillPaint.setAntiAlias(true);
        skillPaint.setTextAlign(Paint.Align.LEFT);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/jian_luobo.ttf");
        skillPaint.setTypeface(typeface);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.7f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/xindexingcao57.ttf");
                skillPaint.setTypeface(typeface);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawGrade(canvas);
    }

    private void drawGrade(Canvas canvas) {
        float curR = (float) (1.1 * radius);
        for (int i = 0; i < count; i++) {
            if (i >= count / 2){
                skillPaint.setTextAlign(Paint.Align.RIGHT);
            }
            float curX = (float) (centerX + curR * Math.cos(angle * i));
            float curY = (float) (centerY + curR * Math.sin(angle * i));
            canvas.drawText(skill[i], curX, curY, skillPaint);
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(100);
        for (int i = 0; i < count; i++) {
            double percent = values[i] / maxValue;
            float curX = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float curY = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(curX, curY);
            } else {
                path.lineTo(curX, curY);
            }
        }
        path.close();
        canvas.drawPath(path, valuePaint);
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float curX = (float) (centerX + radius * Math.cos(angle * i));
            float curY = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(curX, curY);
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count);//r是蜘蛛丝之间的间距
        for (int i = 1; i <= count; i++) {//中心点不用绘制
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    float curX = (float) (centerX + curR * Math.cos(angle * j));
                    float curY = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(curX, curY);
                }
            }
            path.close();
            canvas.drawPath(path, radarPaint);
        }
    }

}
