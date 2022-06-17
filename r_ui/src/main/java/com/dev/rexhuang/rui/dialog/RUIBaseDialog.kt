package com.dev.rexhuang.rui.dialog

import android.content.Context
import android.content.res.Resources
import android.view.Window
import androidx.appcompat.app.AppCompatDialog

/**
 * *  created by RexHuang
 * *  on 2020/8/21
 */
open class RUIBaseDialog : AppCompatDialog {

    constructor(context: Context, theme: Int) : super(context, theme) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun setCancelable(flag: Boolean) {
        super.setCancelable(flag)
    }

    override fun setCanceledOnTouchOutside(cancel: Boolean) {
        super.setCanceledOnTouchOutside(cancel)
    }
}