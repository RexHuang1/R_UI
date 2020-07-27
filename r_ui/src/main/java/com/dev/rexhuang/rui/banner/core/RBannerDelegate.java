package com.dev.rexhuang.rui.banner.core;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.dev.rexhuang.rui.R;
import com.dev.rexhuang.rui.banner.RBanner;
import com.dev.rexhuang.rui.banner.indicator.IRIndicator;
import com.dev.rexhuang.rui.banner.indicator.RCircleIndicator;

import java.util.List;

/**
 * Banner的控制器
 * 辅助Banner完成各种功能的控制
 * 将Banner的一些逻辑内聚在这，保证暴露给使用者的HiBanner干净整洁
 * *  created by RexHuang
 * *  on 2020/7/27
 */
public class RBannerDelegate implements IRBanner, ViewPager.OnPageChangeListener {
    private Context context;
    private RBanner mRBanner;
    private RBannerAdapter mAdapter;
    private IRIndicator<?> mRIndicator;
    private boolean mAutoPlay;
    private boolean mLoop;
    private List<? extends RBannerMo> mRBannerMos;
    private ViewPager.OnPageChangeListener mOnPageChangerListener;
    private int mIntervalTime = 5000;
    private IRBanner.OnBannerClickListener mOnBannerClickListener;
    private RViewPager mRViewPager;
    private int mScrollDuration = -1;

    public RBannerDelegate(Context context, RBanner rBanner) {
        this.context = context;
        mRBanner = rBanner;

    }

    @Override
    public void setBannerData(int layoutResId, @NonNull List<? extends RBannerMo> models) {
        mRBannerMos = models;
        init(layoutResId);
    }


    @Override
    public void setBannerData(@NonNull List<? extends RBannerMo> models) {
        mRBannerMos = models;
        init(R.layout.r_banner_item_image);
    }

    @Override
    public void setRIndicator(IRIndicator<?> rIndicator) {
        mRIndicator = rIndicator;
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        mAutoPlay = autoPlay;
        if (mAdapter != null) {
            mAdapter.setAutoPlay(autoPlay);
        }
        if (mRViewPager != null) {
            mRViewPager.setAutoPlay(autoPlay);
        }
    }

    @Override
    public void setLoop(boolean loop) {
        mLoop = loop;
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        if (intervalTime > 0) {
            this.mIntervalTime = intervalTime;
        }
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        mAdapter.setBindAdapter(bindAdapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangerListener = onPageChangeListener;
    }

    @Override
    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.mOnBannerClickListener = onBannerClickListener;
    }

    @Override
    public void setScrollDuration(int duration) {
        this.mScrollDuration = duration;
        if (mRViewPager != null && duration > 0) mRViewPager.setScrollDuration(duration);
    }

    private void init(int layoutResId) {
        if (mAdapter == null) {
            mAdapter = new RBannerAdapter(context);
        }
        if (mRIndicator == null) {
            mRIndicator = new RCircleIndicator(context);
        }
        mRIndicator.onInflate(mRBannerMos.size());
        mAdapter.setLayoutResId(layoutResId);
        mAdapter.setBannerData(mRBannerMos);
        mAdapter.setAutoPlay(mAutoPlay);
        mAdapter.setLoop(mLoop);
        mAdapter.setBannerClickListener(mOnBannerClickListener);

        mRViewPager = new RViewPager(context);
        mRViewPager.setIntervalTime(mIntervalTime);
        mRViewPager.addOnPageChangeListener(this);
        mRViewPager.setAutoPlay(mAutoPlay);
        mRViewPager.setAdapter(mAdapter);
        if (mScrollDuration > 0) mRViewPager.setScrollDuration(mScrollDuration);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if ((mLoop || mAutoPlay) && mAdapter.getRealCount() != 0) {
            // 无限轮播关键点:使第一张能反向滑动到最后一张.以达到无限滚动的效果
            int firstItem = mAdapter.getFirstItem();
            mRViewPager.setCurrentItem(firstItem, false);
        }
        // 清除缓存View
        mRBanner.removeAllViews();
        mRBanner.addView(mRViewPager, layoutParams);
        mRBanner.addView(mRIndicator.get(), layoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (null != mOnPageChangerListener && mAdapter.getRealCount() != 0) {
            mOnPageChangerListener.onPageScrolled(position % mAdapter.getRealCount(), positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mAdapter.getRealCount() == 0) {
            return;
        }
        position = position % mAdapter.getRealCount();
        if (mOnPageChangerListener != null) {
            mOnPageChangerListener.onPageSelected(position);
        }
        if (mRIndicator != null) {
            mRIndicator.onPointChange(position, mAdapter.getRealCount());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangerListener != null) {
            mOnPageChangerListener.onPageScrollStateChanged(state);
        }
    }
}
