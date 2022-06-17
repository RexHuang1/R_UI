package com.dev.rexhuang.rui.dialog;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * *  created by RexHuang
 * *  on 2020/8/22
 */
public class RUIDisplayHelper {
    /**
     * 获取 DisplayMetrics
     *
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }
}
