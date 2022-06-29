package com.dev.rexhuang.r_ui.app.input

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 **  created by RexHuang
 **  on 2022/6/29
 */
class IconFontView @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet?,
    defStyleAttr:Int = 0
):AppCompatTextView(context, attributeSet, defStyleAttr) {

    init {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/iconfont.ttf")
        setTypeface(typeface)
    }
}