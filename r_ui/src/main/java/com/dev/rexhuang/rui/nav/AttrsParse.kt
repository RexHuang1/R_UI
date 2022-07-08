package com.dev.rexhuang.rui.nav

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import com.dev.rexhuang.rlib.util.RDisplayUtil
import com.dev.rexhuang.rlib.util.RResUtil
import com.dev.rexhuang.rui.R

/**
 **  created by RexHuang
 **  on 2022/7/8
 */
internal object AttrsParse {
    fun parseNavAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int): Attrs {
        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.RNavigationStyle, value, true)

        //xml-->theme.navigationStyle---navigationstyle
        val defStyleRes = if (value.resourceId != 0) value.resourceId else R.style.navigationStyle
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.RNavigationBar,
            defStyleAttr,
            defStyleRes
        )

        val navIcon = array.getString(R.styleable.RNavigationBar_nav_icon)
        val navIconColor = array.getColor(R.styleable.RNavigationBar_nav_icon_color, Color.BLACK)
        val navIconSize = array.getDimensionPixelSize(
            R.styleable.RNavigationBar_nav_icon_size,
            RDisplayUtil.sp2px(18f)
        )
        val navTitle = array.getString(R.styleable.RNavigationBar_nav_title)
        val navSubTitle = array.getString(R.styleable.RNavigationBar_nav_subtitle)
        val horPadding = array.getDimensionPixelSize(R.styleable.RNavigationBar_hor_padding, 0)
        val btnTextSize = array.getDimensionPixelSize(
            R.styleable.RNavigationBar_text_btn_text_size,
            RDisplayUtil.sp2px(16f)
        )
        val btnTextColor = array.getColorStateList(R.styleable.RNavigationBar_text_btn_text_color)

        val titleTextSize = array.getDimensionPixelSize(
            R.styleable.RNavigationBar_title_text_size,
            RDisplayUtil.sp2px(17f)
        )
        val titleTextSizeWithSubtitle = array.getDimensionPixelSize(
            R.styleable.RNavigationBar_title_text_size_with_subTitle,
            RDisplayUtil.sp2px(16f)
        )
        val titleTextColor = array.getColor(
            R.styleable.RNavigationBar_title_text_color,
            RResUtil.getColor(R.color.r_tabtop_normal_color)
        )

        val subTitleTextSize = array.getDimensionPixelSize(
            R.styleable.RNavigationBar_subTitle_text_size,
            RDisplayUtil.sp2px(14f)
        )
        val subTitleTextColor = array.getColor(
            R.styleable.RNavigationBar_subTitle_text_color,
            RResUtil.getColor(R.color.r_tabtop_normal_color)
        )

        val lineColor =
            array.getColor(R.styleable.RNavigationBar_nav_line_color, Color.parseColor("#eeeeee"))
        val lineHeight =
            array.getDimensionPixelOffset(R.styleable.RNavigationBar_nav_line_height, 0)


        array.recycle()

        return Attrs(
            navIcon,
            navIconColor,
            navIconSize.toFloat(),
            navTitle,
            navSubTitle,
            horPadding,
            btnTextSize.toFloat(),
            btnTextColor,
            titleTextSize.toFloat(),
            titleTextSizeWithSubtitle.toFloat(),
            titleTextColor,
            subTitleTextSize.toFloat(),
            subTitleTextColor,
            lineColor,
            lineHeight
        )
    }

    data class Attrs(
        val navIconStr: String?,
        val navIconColor: Int,
        val navIconSize: Float,
        val navTitle: String?,
        val navSubtitle: String?,
        val horPadding: Int,
        val btnTextSize: Float,
        val btnTextColor: ColorStateList?,
        val titleTextSize: Float,
        val titleTextSizeWithSubTitle: Float,
        val titleTextColor: Int,
        val subTitleSize: Float,
        val subTitleTextColor: Int,
        val lineColor: Int,
        val lineHeight: Int
    )
}