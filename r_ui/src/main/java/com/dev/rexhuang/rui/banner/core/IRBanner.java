package com.dev.rexhuang.rui.banner.core;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.dev.rexhuang.rui.banner.indicator.IRIndicator;

import java.util.List;

/**
 * *  created by RexHuang
 * *  on 2020/7/27
 */
public interface IRBanner {

    void setBannerData(@LayoutRes int layoutResId, @NonNull List<? extends RBannerMo> models);

    void setBannerData(@NonNull List<? extends RBannerMo> models);

    void setRIndicator(IRIndicator<?> rIndicator);

    void setAutoPlay(boolean autoPlay);

    void setLoop(boolean loop);

    void setIntervalTime(int intervalTime);

    void setBindAdapter(IBindAdapter bindAdapter);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener);

    void setOnBannerClickListener(OnBannerClickListener onBannerClickListener);

    void setScrollDuration(int duration);

    interface OnBannerClickListener {
        void onBannerClick(@NonNull RBannerAdapter.RBannerViewHolder viewHolder, @NonNull RBannerMo bannerMo, int position);
    }
}
