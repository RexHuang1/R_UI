package com.dev.rexhuang.rui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.R;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public class RTextOverView extends ROverView {
    private TextView mText;
    private View mRotateView;

    public RTextOverView(@NonNull Context context) {
        super(context);
    }

    public RTextOverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RTextOverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.r_refresh_overview, this, true);
        mText = findViewById(R.id.text);
        mRotateView = findViewById(R.id.iv_rotate);
    }

    @Override
    protected void onScroll(int scrollY, int pullRefreshHeight) {

    }

    @Override
    protected void onVisible() {
        mText.setText("下拉刷新");
    }

    @Override
    public void onOver() {
        mText.setText("松开刷新");

    }

    @Override
    public void onRefresh() {
        mText.setText("正在刷新");
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        animation.setInterpolator(linearInterpolator);
        mRotateView.startAnimation(animation);
    }

    @Override
    public void onFinish() {
        mRotateView.clearAnimation();
    }
}
