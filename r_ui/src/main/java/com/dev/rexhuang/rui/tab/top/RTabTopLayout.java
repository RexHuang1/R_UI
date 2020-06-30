package com.dev.rexhuang.rui.tab.top;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.rexhuang.rui.tab.common.IRTabLayout;
import com.dev.rexhuang.rlib.util.RDisplayUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RTabTopLayout extends HorizontalScrollView implements IRTabLayout<RTabTop, RTabTopInfo<?>> {

    private List<OnTabSelectedListener<RTabTopInfo<?>>> tabSelectedListeners = new ArrayList<>();
    private RTabTopInfo<?> selectedInfo;
    private List<RTabTopInfo<?>> infoList;
    private int tabWidth;

    public RTabTopLayout(@NonNull Context context) {
        this(context, null);
    }

    public RTabTopLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RTabTopLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public RTabTop findTab(RTabTopInfo<?> tabInfo) {
        ViewGroup topLayout = getRootLayout(false);
        for (int i = 0; i < topLayout.getChildCount(); i++) {
            if (topLayout.getChildAt(i) instanceof RTabTop) {
                RTabTop tabTop = (RTabTop) topLayout.getChildAt(i);
                if (tabTop.getTabTopInfo() == tabInfo) {
                    return tabTop;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectedChangedListener(OnTabSelectedListener<RTabTopInfo<?>> tabSelectedListener) {
        tabSelectedListeners.add(tabSelectedListener);
    }

    @Override
    public void defaultSelected(@NonNull RTabTopInfo<?> defaultTabInfo) {
        onSelected(defaultTabInfo);
    }

    @Override
    public void inflateInfo(@NonNull List<RTabTopInfo<?>> tabInfoList) {
        if (tabInfoList.isEmpty()) {
            return;
        }
        infoList = tabInfoList;
        LinearLayout rootLayout = getRootLayout(true);
        selectedInfo = null;
        Iterator<OnTabSelectedListener<RTabTopInfo<?>>> iterator = tabSelectedListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof RTabTop) {
                iterator.remove();
            }
        }
        for (int i = 0; i < infoList.size(); i++) {
            final RTabTopInfo<?> tabTopInfo = infoList.get(i);
            RTabTop tabTop = new RTabTop(getContext());
            tabTop.setRTabInfo(tabTopInfo);
            rootLayout.addView(tabTop);
            addTabSelectedChangedListener(tabTop);
            tabTop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RTabTopLayout.this.onSelected(tabTopInfo);
                }
            });
        }
    }

    private LinearLayout getRootLayout(boolean clear) {
        LinearLayout rootLayout = (LinearLayout) getChildAt(0);
        if (rootLayout == null) {
            rootLayout = new LinearLayout(getContext());
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(rootLayout, layoutParams);
        } else if (clear) {
            rootLayout.removeAllViews();
        }
        return rootLayout;
    }


    private void onSelected(RTabTopInfo<?> nextInfo) {
        for (OnTabSelectedListener<RTabTopInfo<?>> tabSelectedListener : tabSelectedListeners) {
            tabSelectedListener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        selectedInfo = nextInfo;
        autoScroll(nextInfo);
    }

    private void autoScroll(RTabTopInfo<?> nextInfo) {
        RTabTop tabTop = findTab(nextInfo);
        if (tabTop == null) return;
        int index = infoList.indexOf(nextInfo);
        int[] loc = new int[2];
        tabTop.getLocationInWindow(loc);
        int scrollWidth;
        if (tabWidth == 0) {
            tabWidth = tabTop.getWidth();
        }
        if ((loc[0] + tabWidth / 2) > RDisplayUtil.getDisplayWidthInPx(getContext()) / 2) {
            scrollWidth = rangeScrollWidth(index, 2);
        } else {
            scrollWidth = rangeScrollWidth(index, -2);
        }
        scrollTo(getScrollX() + scrollWidth, 0);
    }

    private int rangeScrollWidth(int index, int range) {
        int scrollWidth = 0;
        for (int i = 0; i <= Math.abs(range); i++) {
            int next;
            if (range > 0) {
                next = range - i + index;
            } else {
                next = range + i + index;
            }
            if (next >= 0 && next < infoList.size()) {
                if (range > 0) {
                    scrollWidth += scrollWidth(next, true);
                } else {
                    scrollWidth -= scrollWidth(next, false);
                }
            }
        }
        return scrollWidth;
    }

    private int scrollWidth(int next, boolean toRight) {
        RTabTop tabTop = findTab(infoList.get(next));
        Rect rect = new Rect();
        tabTop.getLocalVisibleRect(rect);
        if (toRight) {
            if (rect.right > tabWidth) {
                return tabWidth;
            } else {
                return tabWidth - rect.right;
            }
        } else {
            if (rect.left <= -tabWidth) {
                return tabWidth;
            } else if (rect.left > 0) {
                return rect.left;
            }
        }
        return 0;
    }

}
