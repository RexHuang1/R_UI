package com.dev.rexhuang.rui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.R;
import com.dev.rexhuang.rui.tab.common.IRTab;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RTabBottom extends RelativeLayout implements IRTab<RTabBottomInfo<?>> {

    private RTabBottomInfo tabBottomInfo;

    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    public RTabBottom(@NonNull Context context) {
        this(context, null);
    }

    public RTabBottom(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RTabBottom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.r_tab_bottom_item, this);
        tabImageView = findViewById(R.id.iv_image);
        tabIconView = findViewById(R.id.tv_icon);
        tabNameView = findViewById(R.id.tv_name);
    }

    @Override
    public void setRTabInfo(RTabBottomInfo<?> tabInfo) {
        this.tabBottomInfo = tabInfo;
        inflateInfo(false, true);
    }

    private void inflateInfo(boolean select, boolean init) {
        if (tabBottomInfo.tabType == RTabBottomInfo.TabType.ICON) {
            if (init) {
                tabImageView.setVisibility(GONE);
                tabIconView.setVisibility(VISIBLE);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabBottomInfo.iconFont);
                tabIconView.setTypeface(typeface);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    tabNameView.setText(tabBottomInfo.name);
                }
            }
            if (select) {
                tabIconView.setText(TextUtils.isEmpty(tabBottomInfo.selectedIconName) ? tabBottomInfo.defaultIconName : tabBottomInfo.selectedIconName);
                tabIconView.setTextColor(getTextColor(tabBottomInfo.selectedColor));
                tabNameView.setTextColor(getTextColor(tabBottomInfo.selectedColor));
            } else {
                tabIconView.setText(tabBottomInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabBottomInfo.defaultColor));
                tabNameView.setTextColor(getTextColor(tabBottomInfo.defaultColor));
            }
        } else if (tabBottomInfo.tabType == RTabBottomInfo.TabType.BITMAP) {
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabBottomInfo.name)) {
                    tabNameView.setText(tabBottomInfo.name);
                }
            }
            if (select) {
                tabImageView.setImageBitmap(tabBottomInfo.selectedBitmap == null ? tabBottomInfo.defaultBitmap : tabBottomInfo.selectedBitmap);
                tabNameView.setTextColor(getTextColor(tabBottomInfo.selectedColor));
            } else {
                tabImageView.setImageBitmap(tabBottomInfo.defaultBitmap);
                tabNameView.setTextColor(getTextColor(tabBottomInfo.defaultColor));
            }
        }
    }

    public RTabBottomInfo getTabBottomInfo() {
        return tabBottomInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabIconView() {
        return tabIconView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(View.GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable RTabBottomInfo<?> prevInfo, @NonNull RTabBottomInfo<?> nextInfo) {
        if (prevInfo == nextInfo || prevInfo != tabBottomInfo && nextInfo != tabBottomInfo) {
            return;
        }
        if (prevInfo == tabBottomInfo) {
            inflateInfo(false, false);
        } else {
            inflateInfo(true, false);
        }
    }

    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
