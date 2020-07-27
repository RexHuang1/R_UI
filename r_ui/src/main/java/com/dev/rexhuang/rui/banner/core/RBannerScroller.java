package com.dev.rexhuang.rui.banner.core;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * *  created by RexHuang
 * *  on 2020/7/27
 */
public class RBannerScroller extends Scroller {

    /**
     * 值越大,滑动越慢
     */
    private int mDuration = 1000;
    public RBannerScroller(Context context, int duration) {
        super(context);
        mDuration = duration;
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy,mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
