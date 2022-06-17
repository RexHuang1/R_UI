package com.dev.rexhuang.rui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView
import com.dev.rexhuang.rui.R

/**
 **  created by RexHuang
 **  on 2020/8/21
 */
class RUIDialog : RUIBaseDialog {

    constructor(context: Context) : this(context, R.style.RUI_Dialog) {
    }

    constructor(context: Context, style: Int = R.style.RUI_Dialog) : super(context, style) {
        init()
    }


    fun init() {
        window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0000FF")))
        setCancelable(true)
        setCanceledOnTouchOutside(true)
    }

    companion object {
        class MessageDialogBuilder(context: Context) :
            RUIDialogBuilder<MessageDialogBuilder>(context) {
            var mMessage: String? = null

            fun setMessage(message: String): MessageDialogBuilder {
                mMessage = message
                return this
            }

            override fun onCreateContent(
                dialog: RUIDialog,
                parent: RUIDialogView?,
                context: Context
            ): View? {
                if (mMessage != null && mMessage!!.isNotEmpty()) {
                    var textView = TextView(context)
                    RUIResHelper.assignTextViewWithAttr(
                        textView,
                        R.style.style_Dialog_MessageContent
                    )
                    textView.text = mMessage
//                    textView.movementMethod
                    val scrollView = ScrollView(context)
                    scrollView.addView(textView)
                    scrollView.isVerticalScrollBarEnabled = false
                    return scrollView
                }
                return null
            }
        }
    }
}