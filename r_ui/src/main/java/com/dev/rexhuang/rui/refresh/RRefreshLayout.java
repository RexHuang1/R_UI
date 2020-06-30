package com.dev.rexhuang.rui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public class RRefreshLayout extends FrameLayout implements RRefresh {
    private ROverView.RRefreshState mState;
    private GestureDetector mGestureDetector;
    private RRefresh.RRefreshListener mRefreshListener;
    ;
    protected ROverView mOverView;
    private int mLastY;
    private boolean disableRefreshScroll;
    private RGestureDetector mRGestureDetector = new RGestureDetector() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (Math.abs(distanceX) > Math.abs(distanceY) || mRefreshListener != null && !mRefreshListener.enableRefresh()) {
                // 横向滚动,或刷新被禁止则不处理
                return false;
            }
            if (disableRefreshScroll && mState == ROverView.RRefreshState.STATE_REFRESH) {
                // 刷新时是否禁止滚动
                return true;
            }
            View head = getChildAt(0);
            View child = RScrollUtil.findScrollableChild(RRefreshLayout.this);
            if (RScrollUtil.childScrolled(child)) {
                // 如果列表发生了滚动则不处理
                return false;
            }
            // 没有刷新或没有达到可以刷新的距离,且头部已经划出或下拉
            if ((mState != ROverView.RRefreshState.STATE_REFRESH || head.getBottom() <= mOverView.mPullRefreshHeight) && (head.getBottom() > 0 || distanceY <= 0.0F)) {
                // 还在滑动中
                if (mState != ROverView.RRefreshState.STATE_OVER_RELEASE) {
                    int speed;
                    // 阻尼计算
                    if (child.getTop() < mOverView.mPullRefreshHeight) {
                        speed = (int) (mLastY / mOverView.minDamp);
                    } else {
                        speed = (int) (mLastY / mOverView.maxDamp);
                    }
                    // 如果是正在刷新状态,则不允许在滑动的时候改变状态
                    boolean bool = moveDown(speed, true);
                    mLastY = (int) -distanceY;
                    return bool;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    /**
     * 根据便宜两移动header与child
     *
     * @param offsetY 偏移量
     * @param nonAuto 是否非自动滚动触发
     * @return
     */
    private boolean moveDown(int offsetY, boolean nonAuto) {
        View head = getChildAt(0);
        View child = getChildAt(1);
        int childTop = child.getTop() + offsetY;
        if (childTop <= 0) {
            // 异常情况的补充
            offsetY = -child.getTop();
            // 移动head与child的位置到原始位置
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
            if (mState != ROverView.RRefreshState.STATE_REFRESH) {
                mState = ROverView.RRefreshState.STATE_INIT;
            }
        } else if (mState == ROverView.RRefreshState.STATE_REFRESH && childTop > mOverView.mPullRefreshHeight) {
            // 如果正在下拉刷新中,禁止继续下拉
            return false;
        } else if (childTop <= mOverView.mPullRefreshHeight) {
            if (mOverView.getState() != ROverView.RRefreshState.STATE_VISIBLE && nonAuto) {
                mOverView.onVisible();
                mOverView.setState(ROverView.RRefreshState.STATE_VISIBLE);
                mState = ROverView.RRefreshState.STATE_VISIBLE;
            }
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
            if (childTop == mOverView.mPullRefreshHeight && mState == ROverView.RRefreshState.STATE_OVER_RELEASE) {
                // 下拉刷新完成
                refresh();
            }
        } else {
            if (mOverView.getState() != ROverView.RRefreshState.STATE_OVER && nonAuto) {
                // 超出刷新位置
                mOverView.onOver();
                mOverView.setState(ROverView.RRefreshState.STATE_OVER);
            }
            head.offsetTopAndBottom(offsetY);
            child.offsetTopAndBottom(offsetY);
        }
        if (mOverView != null) {
            mOverView.onScroll(head.getBottom(), mOverView.mPullRefreshHeight);
        }
        return true;
    }

    /**
     * 开始刷新
     */
    private void refresh() {
        if (mRefreshListener != null) {
            mState = ROverView.RRefreshState.STATE_REFRESH;
            mOverView.onRefresh();
            mRefreshListener.onRefresh();
            mOverView.setState(ROverView.RRefreshState.STATE_REFRESH);
        }
    }

    private AutoScroller mAutoScroller;

    public RRefreshLayout(@NonNull Context context) {
        super(context);
        init();

    }

    public RRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mGestureDetector = new GestureDetector(getContext(), mRGestureDetector);
        mAutoScroller = new AutoScroller();
    }

    @Override
    public void setDisableRefreshScroll(boolean disableRefreshScroll) {
        this.disableRefreshScroll = disableRefreshScroll;
    }

    @Override
    public void refreshFinished() {
        View head = getChildAt(0);
        mOverView.onFinish();
        mOverView.setState(ROverView.RRefreshState.STATE_INIT);
        int bottom = head.getBottom();
        if (bottom > 0) {
            recover(bottom);
        }
        mState = ROverView.RRefreshState.STATE_INIT;
    }

    @Override
    public void setRefreshListener(RRefreshListener refreshListener) {
        this.mRefreshListener = refreshListener;
    }

    @Override
    public void setRefreshOverView(ROverView overView) {
        if (mOverView != null) {
            removeView(mOverView);
        }
        this.mOverView = overView;
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mOverView, 0, params);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View head = getChildAt(0);
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_POINTER_INDEX_MASK) {
            // 松开手
            if (head.getBottom() > 0) {
                if (mState != ROverView.RRefreshState.STATE_REFRESH) {
                    // 非刷新状态
                    recover(head.getBottom());
                    return false;
                }
            }
            mLastY = 0;
        }
        boolean consumed = mGestureDetector.onTouchEvent(ev);
        if (consumed || mState != ROverView.RRefreshState.STATE_INIT && mState != ROverView.RRefreshState.STATE_REFRESH && head.getBottom() != 0) {
            ev.setAction(MotionEvent.ACTION_CANCEL);
            return super.dispatchTouchEvent(ev);
        }
        if (consumed) {
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    private void recover(int dis) {
        if (mRefreshListener != null && dis > mOverView.mPullRefreshHeight) {
            // 滚动到指定位置dis-mOverView.mPullRefreshHeight
            mAutoScroller.recover(dis - mOverView.mPullRefreshHeight);
            mState = ROverView.RRefreshState.STATE_OVER_RELEASE;
        } else {
            mAutoScroller.recover(dis);
        }
    }

    private class AutoScroller implements Runnable {
        private Scroller mScroller;
        private int mLastY;
        private boolean mIsFinished;

        public AutoScroller() {
            mScroller = new Scroller(getContext(), new LinearInterpolator());
            mIsFinished = true;
        }

        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                // 还未滚动完成
                moveDown(mLastY - mScroller.getCurrY(), false);
                mLastY = mScroller.getCurrY();
                post(this);
            } else {
                removeCallbacks(this);
                mIsFinished = true;
            }
        }

        void recover(int dis) {
            if (dis < 0) {
                return;
            }
            removeCallbacks(this);
            mLastY = 0;
            mIsFinished = false;
            mScroller.startScroll(0, 0, 0, dis, 300);
            post(this);
        }

        boolean isFinished() {
            return mIsFinished;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        View head = getChildAt(0);
        View child = getChildAt(1);
        if (head != null && child != null) {
            int childTop = child.getTop();
            if (mState == ROverView.RRefreshState.STATE_REFRESH) {
                head.layout(0, mOverView.mPullRefreshHeight - head.getMeasuredHeight(), right, mOverView.mPullRefreshHeight);
                child.layout(0, mOverView.mPullRefreshHeight, right, mOverView.mPullRefreshHeight + child.getMeasuredHeight());
            } else {
                head.layout(0, childTop - head.getMeasuredHeight(), right, childTop);
                child.layout(0, childTop, right, childTop + child.getMeasuredHeight());
            }
            View other;
            for (int i = 2; i < getChildCount(); i++) {
                other= getChildAt(i);
                other.layout(0,top,right,bottom);
            }
        }
    }
}
