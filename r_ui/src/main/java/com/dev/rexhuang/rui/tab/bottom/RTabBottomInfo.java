package com.dev.rexhuang.rui.tab.bottom;

import android.graphics.Bitmap;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RTabBottomInfo<Color> {

    public enum TabType {
        BITMAP, ICON
    }

    public String name;
    public TabType tabType;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public String iconFont;
    public String defaultIconName;
    public String selectedIconName;
    public Color defaultColor;
    public Color selectedColor;

    public RTabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap, Color defaultColor, Color selectedColor) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
        this.tabType = TabType.BITMAP;
    }

    public RTabBottomInfo(String name, String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color selectedColor) {
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
        this.tabType = TabType.ICON;
    }
}
