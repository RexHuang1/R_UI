package com.dev.rexhuang.rui.banner.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.dev.rexhuang.rlib.util.RDisplayUtil
import com.dev.rexhuang.rui.R

/**
 **  created by RexHuang
 **  on 2020/7/27
 */
class RCircleIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IRIndicator<FrameLayout> {

    /**
     * 正常状态下的指示点
     */
    @DrawableRes
    private val mPointNormal: Int = R.drawable.shape_point_normal;
    /**
     * 选中状态下的指示点
     */

    /**
     * 指示点左右内间距
     */
    private var mPointLeftRightPadding: Int = 0;
    /**
     * 指示点上下内间距
     */
    private var mPointTopBottomPadding: Int = 0;

    init {
        mPointLeftRightPadding = RDisplayUtil.dp2px(5f, getContext().resources)
        mPointTopBottomPadding = RDisplayUtil.dp2px(5f, getContext().resources)
    }

    @DrawableRes
    private val mPointSelected: Int = R.drawable.shape_point_select;

    companion object {
        private const val VWC: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun get(): FrameLayout {
        return this
    }

    override fun onInflate(count: Int) {
        removeAllViews()
        if (count <= 0) return

        val groupView = LinearLayout(context)
        groupView.orientation = LinearLayout.HORIZONTAL

        var imageView: ImageView
        val imageViewParams = LinearLayout.LayoutParams(VWC, VWC)
        imageViewParams.gravity = Gravity.CENTER_VERTICAL
        imageViewParams.setMargins(
            mPointLeftRightPadding,
            mPointTopBottomPadding,
            mPointLeftRightPadding,
            mPointTopBottomPadding
        )

        for (i in 0 until count) {
            imageView = ImageView(context)
            imageView.layoutParams = imageViewParams
            if (i == 0) {
                imageView.setBackgroundResource(mPointSelected)
            } else {
                imageView.setBackgroundColor(mPointNormal)
            }
            groupView.addView(imageView)
        }
        val groupViewParams = LayoutParams(VWC, VWC)
        groupViewParams.gravity = Gravity.CENTER or Gravity.BOTTOM
        addView(groupView, groupViewParams)

    }

    override fun onPointChange(current: Int, count: Int) {
        val viewGroup: ViewGroup = getChildAt(0) as ViewGroup
        for (i in 0 until count) {
            val imageView: ImageView = viewGroup.getChildAt(i) as ImageView
            if (i == current) {
                imageView.setBackgroundResource(mPointSelected)
            } else {
                imageView.setBackgroundResource(mPointNormal)
            }
            imageView.requestLayout()
        }
    }
}