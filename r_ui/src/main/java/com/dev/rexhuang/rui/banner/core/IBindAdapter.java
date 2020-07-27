package com.dev.rexhuang.rui.banner.core;

/**
 *
 * Banner的数据绑定接口，基于该接口可以实现数据的绑定和框架层解耦
 * *  created by RexHuang
 * *  on 2020/7/27
 */
public interface IBindAdapter {
    void onBind(RBannerAdapter.RBannerViewHolder viewHolder, RBannerMo mo, int position);
}
