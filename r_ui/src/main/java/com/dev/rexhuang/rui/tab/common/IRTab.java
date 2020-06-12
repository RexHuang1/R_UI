package com.dev.rexhuang.rui.tab.common;

import androidx.annotation.Px;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public interface IRTab<D> extends IRTabLayout.OnTabSelectedListener<D> {

    void setRTabInfo(D tabInfo);

    void resetHeight(@Px int height);
}
