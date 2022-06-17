package com.dev.rexhuang.rui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.rexhuang.rui.R;
import com.dev.rexhuang.rui.tab.common.IRTabLayout;
import com.dev.rexhuang.rlib.util.RDisplayUtil;
import com.dev.rexhuang.rlib.util.RViewUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RTabBottomLayout extends FrameLayout implements IRTabLayout<RTabBottom, RTabBottomInfo<?>> {

    private List<OnTabSelectedListener<RTabBottomInfo<?>>> tabSelectedListeners = new ArrayList<>();
    private RTabBottomInfo<?> selectedInfo;
    private float bottomAlpha = 1f;
    private static float tabBottomHeight = 50;
    private float bottomLineHeight = 0.5f;
    private String bottomLineColor = "#dfe0e1";
    private List<RTabBottomInfo<?>> infoList;
    private int bottomColor = Color.WHITE;

    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";

    public RTabBottomLayout(@NonNull Context context) {
        this(context, null);
    }

    public RTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public RTabBottom findTab(RTabBottomInfo<?> tabInfo) {
        ViewGroup bottomLayout = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < bottomLayout.getChildCount(); i++) {
            if (bottomLayout.getChildAt(i) instanceof RTabBottom) {
                RTabBottom tabBottom = (RTabBottom) bottomLayout.getChildAt(i);
                if (tabBottom.getTabBottomInfo() == tabInfo) {
                    return tabBottom;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectedChangedListener(OnTabSelectedListener<RTabBottomInfo<?>> tabSelectedListener) {
        tabSelectedListeners.add(tabSelectedListener);
    }

    @Override
    public void defaultSelected(@NonNull RTabBottomInfo<?> defaultTabInfo) {
        onSelected(defaultTabInfo);
    }

    public void setTabAlpha(float alpha) {
        this.bottomAlpha = alpha;
    }

    public void setTabBackground(int bottomColor) {
        this.bottomColor = bottomColor;
    }

    public void setTabHeight(float height) {
        tabBottomHeight = height;
    }

    public void setBottomLineHeight(float height) {
        bottomLineHeight = height;
    }

    public void setBottomLineColor(String color) {
        this.bottomLineColor = color;
    }

    @Override
    public void inflateInfo(@NonNull List<RTabBottomInfo<?>> tabInfoList) {
        if (tabInfoList.isEmpty()) {
            return;
        }
        infoList = tabInfoList;
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        selectedInfo = null;
        addBackground();
        Iterator<OnTabSelectedListener<RTabBottomInfo<?>>> iterator = tabSelectedListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof RTabBottom) {
                iterator.remove();
            }
        }
        int height = RDisplayUtil.dp2px(tabBottomHeight, getResources());
        int width = RDisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();
        FrameLayout fl = new FrameLayout(getContext());
        fl.setTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < infoList.size(); i++) {
            final RTabBottomInfo<?> tabBottomInfo = infoList.get(i);
            RTabBottom tabBottom = new RTabBottom(getContext());
            tabBottom.setRTabInfo(tabBottomInfo);

            LayoutParams layoutParams = new LayoutParams(width, height);
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.leftMargin = i * width;

            fl.addView(tabBottom, layoutParams);
            addTabSelectedChangedListener(tabBottom);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RTabBottomLayout.this.onSelected(tabBottomInfo);
                }
            });
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(fl, layoutParams);

        fixContentView();
    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));

        LayoutParams bottomLineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) bottomLineHeight);
        bottomLineParams.gravity = Gravity.BOTTOM;
        bottomLineParams.bottomMargin = (int) RDisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine, bottomLineParams);
        bottomLine.setAlpha(bottomAlpha);
    }

    private void addBackground() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.r_tab_bottom_background, null);
        view.setBackgroundColor(bottomColor);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RDisplayUtil.dp2px(tabBottomHeight, getResources()));
        layoutParams.gravity = Gravity.BOTTOM;
        addView(view, layoutParams);
        view.setAlpha(bottomAlpha);
    }

    private void onSelected(RTabBottomInfo<?> nextInfo) {
        for (OnTabSelectedListener<RTabBottomInfo<?>> tabSelectedListener : tabSelectedListeners) {
            tabSelectedListener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        selectedInfo = nextInfo;
    }

    private void fixContentView() {
        if (!(getChildAt(0) instanceof ViewGroup)) {
            return;
        }
        ViewGroup contentView = (ViewGroup) getChildAt(0);
        ViewGroup targetView = RViewUtil.findTypeView(contentView, RecyclerView.class);
        if (targetView == null) {
            targetView = RViewUtil.findTypeView(contentView, ScrollView.class);
        }
        if (targetView == null) {
            targetView = RViewUtil.findTypeView(contentView, AbsListView.class);
        }
        if (targetView != null) {
            targetView.setPadding(0, 0, 0, RDisplayUtil.dp2px(tabBottomHeight, getResources()));
            targetView.setClipToPadding(true);
        }
    }

    public static void clipBottomPadding(ViewGroup targetView) {
        if (targetView != null) {
            targetView.setPadding(0, 0, 0, RDisplayUtil.dp2px(tabBottomHeight));
            targetView.setClipToPadding(false);
        }
    }

}
