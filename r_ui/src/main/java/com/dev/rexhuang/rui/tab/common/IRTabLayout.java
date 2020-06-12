package com.dev.rexhuang.rui.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public interface IRTabLayout<Tab extends ViewGroup, D> {

    Tab findTab(D tabInfo);

    void addTabSelectedChangedListener(OnTabSelectedListener<D> tabSelectedListener);

    void defaultSelected(@NonNull D defaultTabInfo);

    void inflateInfo(@NonNull List<D> tabInfoList);

    interface OnTabSelectedListener<D> {
        void onTabSelectedChange(int index, @Nullable D prevInfo, @NonNull D nextInfo);
    }
}
