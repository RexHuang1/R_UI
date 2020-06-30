package com.dev.rexhuang.rui.base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public class GetSegmentView extends View {

    private Path mCirclePath, mDstPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Float mCurAnimValue;

    public GetSegmentView(Context context) {
        super(context);
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath, true);

        final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.setRepeatCount(-1);
        animator.start();
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5)) * length));
        mDstPath.reset();
        canvas.drawColor(Color.WHITE);
        mPathMeasure.getSegment(start, stop, mDstPath, true);

        canvas.drawPath(mDstPath, mPaint);
    }
}
