package com.dev.rexhuang.rui.banner.core;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * 自动翻页的ViewPager
 *
 * *  created by RexHuang
 * *  on 2020/7/11
 */
public class RViewPager extends ViewPager {
    /**
     * 滚动时间间隔
     */
    private int mIntervalTime;
    /**
     * 是否开启自动轮播
     */
    private boolean mAutoPlay = true;
    private boolean isLayout;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            next();
            mHandler.postDelayed(this, mIntervalTime);
        }
    };


    public RViewPager(@NonNull Context context) {
        super(context);
    }

    public RViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoPlay(boolean autoPlay) {
        mAutoPlay = autoPlay;
        if (!mAutoPlay) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start();
                break;
            default:
                stop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isLayout = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isLayout && getAdapter() != null && getAdapter().getCount() > 0) {
            try {
                // fix 使用Recyclerview + ViewPager bug
                Field mScroller = ViewPager.class.getDeclaredField("mFirstLayout");
                mScroller.setAccessible(true);
                mScroller.set(this, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        // fix 使用Recyclerview + ViewPager bug
        if (((Activity) getContext()).isFinishing()) {
            super.onDetachedFromWindow();
        }
        stop();
    }

    /**
     * 设置ViewPager的滚动速度
     *
     * @param duration page切换的时间长度
     */
    public void setScrollDuration(int duration) {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this, new RBannerScroller(getContext(), duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面停留时间
     *
     * @param intervalTime 停留时间单位毫秒
     */
    public void setIntervalTime(int mIntervalTime) {
        this.mIntervalTime = mIntervalTime;
    }

    public void start() {
        mHandler.removeCallbacksAndMessages(null);
        if (mAutoPlay) {
            mHandler.postDelayed(mRunnable, mIntervalTime);
        }
    }

    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 设置下一个要显示的item,并返回item的pos
     *
     * @return 下一个要显示item的pos
     */
    private int next() {
        int nextPosition = -1;
        if (getAdapter() == null || getAdapter().getCount() <= 1) {
            stop();
            return nextPosition;
        }
        nextPosition = getCurrentItem() + 1;
        // 下一个索引大于adapter的view的最大数量时重新开始
        if (nextPosition >= getAdapter().getCount()) {
        // 获取第一个item的索引
            nextPosition = ((RBannerAdapter) getAdapter()).getFirstItem();
        }
        setCurrentItem(nextPosition, true);
        return nextPosition;
    }
}
