package com.dev.rexhuang.rui.tab.top;

import android.content.Context;
import android.graphics.Color;
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
public class RTabTop extends RelativeLayout implements IRTab<RTabTopInfo<?>> {

    private RTabTopInfo tabTopInfo;

    private ImageView tabImageView;
    private TextView tabNameView;
    private View indicator;

    public RTabTop(@NonNull Context context) {
        this(context, null);
    }

    public RTabTop(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RTabTop(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.r_tab_top_item, this);
        tabImageView = findViewById(R.id.iv_image);
        tabNameView = findViewById(R.id.tv_name);
        indicator = findViewById(R.id.tab_top_indicator);
    }

    @Override
    public void setRTabInfo(RTabTopInfo<?> tabInfo) {
        this.tabTopInfo = tabInfo;
        inflateInfo(false, true);
    }

    private void inflateInfo(boolean select, boolean init) {
        if (tabTopInfo.tabType == RTabTopInfo.TabType.TEXT) {
            if (init) {
                tabNameView.setVisibility(VISIBLE);
                tabImageView.setVisibility(GONE);
                if (!TextUtils.isEmpty(tabTopInfo.name)) {
                    tabNameView.setText(tabTopInfo.name);
                }
            }
            if (select) {
                tabNameView.setTextColor(getTextColor(tabTopInfo.selectedColor));
                indicator.setVisibility(VISIBLE);
            } else {
                tabNameView.setTextColor(getTextColor(tabTopInfo.defaultColor));
                indicator.setVisibility(GONE);
            }
        } else if (tabTopInfo.tabType == RTabTopInfo.TabType.BITMAP) {
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabNameView.setVisibility(GONE);
            }
            if (select) {
                tabImageView.setImageBitmap(tabTopInfo.selectedBitmap == null ? tabTopInfo.defaultBitmap : tabTopInfo.selectedBitmap);
                indicator.setVisibility(VISIBLE);
            } else {
                tabImageView.setImageBitmap(tabTopInfo.defaultBitmap);
                indicator.setVisibility(GONE);
            }
        }
    }

    public RTabTopInfo getTabTopInfo() {
        return tabTopInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public View getIndicator() {
        return indicator;
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
    public void onTabSelectedChange(int index, @Nullable RTabTopInfo<?> prevInfo, @NonNull RTabTopInfo<?> nextInfo) {
        if (prevInfo == nextInfo || prevInfo != tabTopInfo && nextInfo != tabTopInfo) {
            return;
        }
        if (prevInfo == tabTopInfo) {
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
