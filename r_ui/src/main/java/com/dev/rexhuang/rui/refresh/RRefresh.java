package com.dev.rexhuang.rui.refresh;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public interface RRefresh {
    /**
     * 刷新时是否禁止滚动
     *
     * @param disableRefreshScroll 否禁止滚动
     */
    void setDisableRefreshScroll(boolean disableRefreshScroll);

    /**
     * 刷新完成
     */
    void refreshFinished();

    /**
     * 设置下拉刷新的监听器
     *
     * @param refreshListener 刷新的监听器
     */
    void setRefreshListener(RRefreshListener refreshListener);

    /**
     * 设置下拉刷新的视图
     *
     * @param overView 下拉刷新的视图
     */
    void setRefreshOverView(ROverView overView);

    interface RRefreshListener {
        void onRefresh();

        boolean enableRefresh();

    }
}
