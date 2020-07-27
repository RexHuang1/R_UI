package com.dev.rexhuang.rui.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dev.rexhuang.rui.R;
import com.dev.rexhuang.rui.banner.core.IBindAdapter;
import com.dev.rexhuang.rui.banner.core.IRBanner;
import com.dev.rexhuang.rui.banner.core.RBannerDelegate;
import com.dev.rexhuang.rui.banner.core.RBannerMo;
import com.dev.rexhuang.rui.banner.indicator.IRIndicator;

import java.util.List;

/**
 * 核心问题：
 * 1. 如何实现UI的高度定制？
 * 2. 作为有限的item如何实现无线轮播呢？
 * 3. Banner需要展示网络图片，如何将网络图片库和Banner组件进行解耦？
 * 4. 指示器样式各异，如何实现指示器的高度定制？
 * 5. 如何设置ViewPager的滚动速度？
 */
public class RBanner extends FrameLayout implements IRBanner {
    private RBannerDelegate delegate;

    public RBanner(@NonNull Context context) {
        this(context, null);
    }

    public RBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new RBannerDelegate(context, this);
        initCustomAttrs(context, attrs);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RBanner);
        boolean autoPlay = typedArray.getBoolean(R.styleable.RBanner_autoPlay, true);
        boolean loop = typedArray.getBoolean(R.styleable.RBanner_loop, true);
        int intervalTime = typedArray.getInteger(R.styleable.RBanner_intervalTime, -1);
        setAutoPlay(autoPlay);
        setLoop(loop);
        setIntervalTime(intervalTime);
        typedArray.recycle();
    }

    @Override
    public void setBannerData(int layoutResId, @NonNull List<? extends RBannerMo> models) {
        delegate.setBannerData(layoutResId, models);
    }

    @Override
    public void setBannerData(@NonNull List<? extends RBannerMo> models) {
        delegate.setBannerData(models);
    }

    @Override
    public void setRIndicator(IRIndicator<?> rIndicator) {
        delegate.setRIndicator(rIndicator);
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        delegate.setAutoPlay(autoPlay);
    }

    @Override
    public void setLoop(boolean loop) {
        delegate.setLoop(loop);
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        delegate.setIntervalTime(intervalTime);
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        delegate.setBindAdapter(bindAdapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        delegate.setOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        delegate.setOnBannerClickListener(onBannerClickListener);
    }

    @Override
    public void setScrollDuration(int duration) {
        delegate.setScrollDuration(duration);
    }
}
