package com.dev.rexhuang.rui.tab.top;

import android.graphics.Bitmap;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RTabTopInfo<Color> {

    public enum TabType {
        BITMAP, TEXT
    }

    public Class<?> fragment;
    public String name;
    public TabType tabType;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public Color defaultColor;
    public Color selectedColor;

    public RTabTopInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap, Color defaultColor, Color selectedColor) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
        this.tabType = TabType.BITMAP;
    }

    public RTabTopInfo(String name, Color defaultColor, Color selectedColor) {
        this.name = name;
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
        this.tabType = TabType.TEXT;
    }

    public RTabTopInfo<Color> setFragment(Class<?> fragment) {
        this.fragment = fragment;
        return this;
    }
}
