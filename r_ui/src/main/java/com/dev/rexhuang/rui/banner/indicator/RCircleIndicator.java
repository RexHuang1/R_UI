package com.dev.rexhuang.rui.banner.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.rexhuang.rlib.util.RDisplayUtil;
import com.dev.rexhuang.rui.R;

/**
 * 圆形指示器
 * *  created by RexHuang
 * *  on 2020/7/27
 */
public class RCircleIndicator extends FrameLayout implements IRIndicator<FrameLayout> {
    private static final int VMC = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * 正常状态下的指示点
     */
    private @DrawableRes
    int mPointNormal = R.drawable.shape_point_normal;
    /**
     * 选中状态下的指示点
     */
    private @DrawableRes
    int mPointSelected = R.drawable.shape_point_select;
    /**
     * 指示点左右内间距
     */
    private int mPointLeftRightPadding;
    /**
     * 指示点上下内间距
     */
    private int mPointTopBottomPadding;

    public RCircleIndicator(@NonNull Context context) {
        this(context, null);
    }

    public RCircleIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RCircleIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPointLeftRightPadding = RDisplayUtil.dp2px(5, getContext().getResources());
        mPointTopBottomPadding = RDisplayUtil.dp2px(5, getContext().getResources());
    }

    @Override
    public FrameLayout get() {
        return this;
    }

    @Override
    public void onInflate(int count) {
        removeAllViews();
        if (count <= 0) {
            return;
        }
        LinearLayout groupView = new LinearLayout(getContext());
        groupView.setOrientation(LinearLayout.HORIZONTAL);

        ImageView imageView;
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(VMC, VMC);
        imageViewParams.gravity = Gravity.CENTER_VERTICAL;
        imageViewParams.setMargins(mPointLeftRightPadding, mPointTopBottomPadding, mPointLeftRightPadding, mPointTopBottomPadding);

        for (int i = 0; i < count; i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(imageViewParams);
            if (i == 0) {
                imageView.setBackgroundResource(mPointSelected);
            } else {
                imageView.setBackgroundResource(mPointNormal);
            }
            groupView.addView(imageView);
        }
        LayoutParams groupViewParams = new LayoutParams(VMC, VMC);
        groupViewParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        addView(groupView, groupViewParams);
    }

    @Override
    public void onPointChange(int current, int count) {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        for (int i = 0; i < count; i++) {
            ImageView imageView = (ImageView) viewGroup.getChildAt(i);
            if (i == current){
                imageView.setBackgroundResource(mPointSelected);
            } else{
                imageView.setBackgroundResource(mPointNormal);
            }
            imageView.requestLayout();
        }
    }
}
