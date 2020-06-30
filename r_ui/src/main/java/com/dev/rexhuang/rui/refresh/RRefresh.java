package com.dev.rexhuang.rui.refresh;

/**
 * *  created by RexHuang
 * *  on 2020/6/30
 */
public interface RRefresh {

    void setDisableRefreshScroll(boolean disableRefreshScroll);

    void refreshFinished();

    void setRefreshListener(RRefreshListener refreshListener);

    void setRefreshOverView(ROverView overView);

    interface RRefreshListener {
        void onRefresh();

        boolean enableRefresh();

    }
}
