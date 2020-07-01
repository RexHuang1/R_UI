package com.dev.rexhuang.rui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.rexhuang.rlib.util.RDisplayUtil;

/**
 * 下拉刷新的Overlay视图,可以重载这个类来定义自己的Overlay
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public abstract class ROverView extends FrameLayout {

    public enum RRefreshState {
        /**
         * 初始态
         */
        STATE_INIT,
        /**
         * Header展示的状态
         */
        STATE_VISIBLE,
        /**
         * 超出可刷新距离的状态
         */
        STATE_OVER,
        /**
         * 超出可刷新距离的状态
         */
        STATE_REFRESH,
        /**
         * 超出刷新位置松开手后的状态
         */
        STATE_OVER_RELEASE
    }

    protected RRefreshState mState = RRefreshState.STATE_INIT;

    /**
     * 触发下拉刷新需要的最小高度
     */
    public int mPullRefreshHeight;
    /**
     * 最小阻尼
     */
    public float minDamp = 1.6f;

    /**
     * 最大阻尼
     */
    public float maxDamp = 2.2f;

    public ROverView(@NonNull Context context) {
        super(context);
        preInit();
    }

    public ROverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        preInit();
    }

    public ROverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        preInit();
    }

    protected void preInit() {
        mPullRefreshHeight = RDisplayUtil.dp2px(66, getResources());
        init();
    }

    /**
     * 初始化
     */
    public abstract void init();

    protected abstract void onScroll(int scrollY, int pullRefreshHeight);

    /**
     * 显示Overlay
     */
    protected abstract void onVisible();

    /**
     * 超过Overlay,释放就会加载
     */
    public abstract void onOver();

    /**
     * 开始刷新
     */
    public abstract void onRefresh();

    /**
     * 加载完成
     */
    public abstract void onFinish();

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(RRefreshState state) {
        this.mState = state;
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public RRefreshState getState() {
        return mState;
    }
}
