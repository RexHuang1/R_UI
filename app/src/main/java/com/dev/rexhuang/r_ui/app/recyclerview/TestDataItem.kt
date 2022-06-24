package com.dev.rexhuang.r_ui.app.recyclerview

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rui.item.RDataItem
import com.dev.rexhuang.rui.item.RViewHolder

/**
 **  继承RDataItem自定义item样式
 **  getItemLayoutRes()和getItemView()任选其一提供item的view
 **  onBindData()提供view和数据的绑定
 **  created by RexHuang
 **  on 2022/6/17
 */
class TestDataItem(text: String) : RDataItem<String, RViewHolder>(text) {
    lateinit var mItemOnClickListener: ItemOnClickListener

    override fun getItemLayoutRes(): Int {
        return R.layout.layout_item
    }

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        val tv = holder.itemView.findViewById<TextView>(R.id.tv)
        tv.text = mData
        tv.setOnClickListener {
            if (mItemOnClickListener != null) {
                mItemOnClickListener!!.onClick()
            }
        }
    }


}

interface ItemOnClickListener {
    open fun onClick()
}