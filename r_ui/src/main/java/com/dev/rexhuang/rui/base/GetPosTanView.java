package com.dev.rexhuang.rui.base;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.R;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public class GetPosTanView extends View {

    private Path mCirclePath, mDstPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Float mCurAnimValue;
    private Bitmap mArrawBmp;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public GetPosTanView(Context context) {
        super(context);
    }

    public GetPosTanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mArrawBmp = BitmapFactory.decodeResource(getResources(), R.drawable.arraw);
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

    public GetPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);


        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
        mDstPath.reset();

        mPathMeasure.getSegment(0, stop, mDstPath, true);
        canvas.drawPath(mDstPath, mPaint);


        /**
         * 箭头旋转、位移实现方式一：
         */
        //计算方位角
//        mPathMeasure.getPosTan(stop, pos, tan);
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degrees, mArrawBmp.getWidth() / 2, mArrawBmp.getHeight() / 2);
//        matrix.postTranslate(pos[0] - mArrawBmp.getWidth() / 2, pos[1] - mArrawBmp.getHeight() / 2);

        /**
         * 箭头旋转、位移实现方式一：
         */
        Matrix matrix = new Matrix();
        mPathMeasure.getMatrix(stop, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.preTranslate(-mArrawBmp.getWidth() / 2, -mArrawBmp.getHeight() / 2);

        canvas.drawBitmap(mArrawBmp, matrix, mPaint);
    }
}
