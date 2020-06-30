package com.dev.rexhuang.rui.base;

import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public class AliPayView extends View {

    private Path mCirclePath, mDstPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mCurAnimValue;
    private int mCentX = 100;
    private int mCentY = 100;
    private int mRadius = 50;

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mCirclePath = new Path();
        mDstPath = new Path();

        mCirclePath.addCircle(mCentX, mCentY, mRadius, Path.Direction.CW);

        mCirclePath.moveTo(mCentX - mRadius / 2, mCentY);
        mCirclePath.lineTo(mCentX, mCentY + mRadius / 2);
        mCirclePath.lineTo(mCentX + mRadius / 2, mCentY - mRadius / 3);

        mPathMeasure = new PathMeasure(mCirclePath, false);

//        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);
        Keyframe keyframe0 = Keyframe.ofFloat(0,0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f,1);
        keyframe1.setInterpolator(new AccelerateInterpolator());
        Keyframe keyframe2 = Keyframe.ofFloat(1,2);
        keyframe2.setInterpolator(new AccelerateDecelerateInterpolator());
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe((String) null, keyframe0, keyframe1, keyframe2);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(valuesHolder);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    boolean mNext = false;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        if (mCurAnimValue < 1) {
            float stop = mPathMeasure.getLength() * mCurAnimValue;
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        } else {
            if (!mNext) {
                mNext = true;
                mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
                mPathMeasure.nextContour();
            }
            float stop = mPathMeasure.getLength() * (mCurAnimValue - 1);
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }
        canvas.drawPath(mDstPath, mPaint);
    }
}
