package com.dev.rexhuang.rui.item

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 **  created by RexHuang
 **  on 2020/7/27
 */
abstract class RDataItem<DATA, VH : RecyclerView.ViewHolder>(data: DATA? = null) {
    val TAG: String = "RDataItem"
    var rAdapter: RAdapter? = null
    var mData: DATA? = null;

    init {
        mData = data;
    }

    /**
     * 绑定数据
     */
    abstract fun onBindData(holder: VH, position: Int)

    /**
     * 返回该item的布局资源id
     */
    open fun getItemLayoutRes(): Int {
        return -1;
    }

    /**
     * 返回该item的视图View
     */
    open fun getItemView(parent: ViewGroup): View? {
        return null;
    }

    fun setAdapter(adapter: RAdapter) {
        this.rAdapter = adapter
    }

    /**
     * 刷新列表
     */
    fun refreshItem() {
        if (rAdapter != null) rAdapter!!.refreshItem(this);
    }

    /**
     * 从列表上移除
     */
    fun removeItem() {
        if (rAdapter != null) rAdapter!!.removeItem(this)
    }

    /**
     * 该item在列表上占几列,代表的宽度是占满屏幕
     */
    open fun getSpanSize(): Int {
        return 0
    }

    /**
     * 该item被滑进屏幕
     */
    open fun onViewAttachedToWindow(holder: VH) {

    }

    /**
     * 该item被滑出屏幕
     */
    open fun onViewDetachedFromWindow(holder: VH) {

    }
}