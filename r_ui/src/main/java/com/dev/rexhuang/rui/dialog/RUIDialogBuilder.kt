package com.dev.rexhuang.rui.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Space
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dev.rexhuang.rui.R

/**
 **  created by RexHuang
 **  on 2020/8/21
 */
abstract class RUIDialogBuilder<T : RUIDialogBuilder<T>>(val context: Context) {
    protected lateinit var mRootView: RUIDialogRootLayout
    protected lateinit var mDialog: RUIDialog
    protected var mTitle: String? = null
    protected lateinit var mDialogView: RUIDialogView
    protected var mCheckKeyboardOverlay = false
    protected var mMaxPercent: Float = 0.75f
    protected var mActions = mutableListOf<RUIDialogAction>()
//        set(value): out RUIDia {
//
//        }

    private var mCancelable = true
    private var mCanceledOnTouchOutside = true

    fun setTitle(title: String): T {
        mTitle = title
        return this as T
    }

    fun hasTitle(): Boolean {
        return !mTitle.isNullOrEmpty()
    }

    fun setCancelable(cancelable: Boolean): T {
        mCancelable = cancelable
        return this as T
    }

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): T {
        mCanceledOnTouchOutside = canceledOnTouchOutside
        return this as T
    }

    fun addAction(action: RUIDialogAction): T {
        mActions.add(action)
        return this as T
    }

    fun show(): RUIDialog {
        val dialog = create()
        dialog.show()
        return dialog
    }

    fun setCheckKeyboardOverlay(checkKeyboardOverlay: Boolean): T {
        mCheckKeyboardOverlay = checkKeyboardOverlay
        return this as T
    }

    fun setMaxPercent(maxPercent: Float): T {
        mMaxPercent = maxPercent
        return this as T
    }

    fun create(): RUIDialog {
        return create(R.style.RUI_Dialog)
    }

    private fun create(@StyleRes style: Int): RUIDialog {
        mDialog = RUIDialog(context, style)
        val dialogContext = mDialog.context

        mDialogView = onCreateDialogView(dialogContext)
        mRootView = RUIDialogRootLayout(dialogContext, mDialogView, onCreateDialogLayoutParams())
        mRootView.setCheckKeyboardOverlay(mCheckKeyboardOverlay)
        mRootView.setOverlayOccurInMeasureCallback {

        }
        mRootView.setMaxPercent(mMaxPercent)
        configRootLayout(mRootView)
        mDialogView = mRootView.dialogView

        //title
        val titleView: View? = onCreateTitle(mDialog, mDialogView, dialogContext)
        val operatorLayout: View? = onCreateOperatorLayout(mDialog, mDialogView, dialogContext)
        val contentLayout: View? = onCreateContent(mDialog, mDialogView, dialogContext)
        checkAndSetId(titleView, R.id.rui_dialog_title_id)
        checkAndSetId(operatorLayout, R.id.rui_dialog_operator_layout_id)
        checkAndSetId(contentLayout, R.id.rui_dialog_content_id)

        // chain
        if (titleView != null) {
            val lp = onCreateTitleLayoutParams(context)
            if (contentLayout != null) {
                lp.bottomToTop = contentLayout.id
            } else if (operatorLayout != null) {
                lp.bottomToTop = operatorLayout.id
            } else {
                lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
            mDialogView.addView(titleView, lp)
        }

        if (contentLayout != null) {
            val lp = onCreateContentLayoutParams(context)
            if (titleView != null) {
                lp.topToBottom = titleView.id
            } else {
                lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            }

            if (operatorLayout != null) {
                lp.bottomToTop = operatorLayout.id
            } else {
                lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
            mDialogView.addView(contentLayout, lp)
        }

        if (operatorLayout != null) {
            val lp = onCreateOperatorLayoutParams(context)
            if (contentLayout != null) {
                lp.topToBottom = contentLayout.id
            } else if (titleView != null) {
                lp.topToBottom = titleView.id
            } else {
                lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            }
            mDialogView.addView(operatorLayout, lp)
        }
        mDialog.addContentView(
            mRootView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        mDialog.setCancelable(mCancelable)
        mDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside)
        return mDialog
    }

    protected open fun onCreateTitleLayoutParams(context: Context): ConstraintLayout.LayoutParams {
        val lp = ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED
        return lp
    }

    protected open fun onCreateContentLayoutParams(context: Context): ConstraintLayout.LayoutParams {
        val lp = ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        lp.constrainedHeight = true
        return lp
    }

    private fun onCreateOperatorLayoutParams(context: Context): ConstraintLayout.LayoutParams {
        val lp = ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        lp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        lp.verticalChainStyle = ConstraintLayout.LayoutParams.CHAIN_PACKED
        return lp
    }

    private fun checkAndSetId(view: View?, id: Int) {
        if (view != null && view.id == View.NO_ID) {
            view.id = id
        }
    }

    abstract fun onCreateContent(dialog: RUIDialog, parent: RUIDialogView?, context: Context): View?

    private fun onCreateOperatorLayout(
        dialog: RUIDialog,
        parent: RUIDialogView,
        context: Context
    ): View? {
        var size = mActions.size
        if (size > 0) {
            val a = context.obtainStyledAttributes(
                R.style.style_Dialog_ActionContainer,
                R.styleable.RUIDialogActionContainerCustomDef
            )
            var count = a.indexCount
            var justifyContent = -1
            var spaceCustomIndex = 0
            var actionHeight = -1
            var actionSpace = 0
            for (i in 0 until count) {
                var attr = a.getIndex(i)
                if (attr == R.styleable.RUIDialogActionContainerCustomDef_rui_dialog_action_container_justify_content) {
                    justifyContent = a.getInteger(attr, justifyContent)
                } else if (attr == R.styleable.RUIDialogActionContainerCustomDef_rui_dialog_action_container_custom_space_index) {
                    spaceCustomIndex = a.getInteger(attr, 0)
                } else if (attr == R.styleable.RUIDialogActionContainerCustomDef_rui_dialog_action_height) {
                    actionHeight = a.getDimensionPixelSize(attr, 0)
                } else if (attr == R.styleable.RUIDialogActionContainerCustomDef_rui_dialog_action_space) {
                    actionSpace = a.getDimensionPixelSize(attr, 0)
                }
            }
            a.recycle()
            var spaceInsertPos = -1
            if (justifyContent == 0) {
                spaceInsertPos = size
            } else if (justifyContent == 1) {
                spaceInsertPos = 0
            } else if (justifyContent == 3) {
                spaceInsertPos = spaceCustomIndex
            }

            val layout = LinearLayout(context)
            layout.id = R.id.rui_dialog_operator_layout_id
            layout.orientation = LinearLayout.HORIZONTAL

            for (i in 0 until size) {
                if (spaceInsertPos == i) {
                    layout.addView(createActionContainerSpace(context))
                }
                val action = mActions[i]
                var actionLp =
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, actionHeight)
                if (spaceInsertPos >= 0) {
                    if (i >= spaceInsertPos) {
                        actionLp.leftMargin = actionSpace
                    } else {
                        actionLp.rightMargin = actionSpace
                    }
                }
                if (justifyContent == 2) {
                    actionLp.weight = 1f
                }
                var actionView: Button = action.buildActionView(mDialog, i)
                layout.addView(actionView, actionLp)
            }

            if (spaceInsertPos == size) {
                layout.addView(createActionContainerSpace(context))
            }
            return layout
        }
        return null
    }

    private fun createActionContainerSpace(context: Context): View? {
        val space = Space(context)
        val spaceLp = LinearLayout.LayoutParams(0, 0)
        spaceLp.weight = 1f
        space.layoutParams = spaceLp
        return space
    }

    private fun onCreateTitle(dialog: RUIDialog, parent: RUIDialogView, context: Context): View? {
        if (hasTitle()) {
            var tv = AppCompatTextView(context)
            tv.id = R.id.rui_dialog_title_id
            tv.text = mTitle
            RUIResHelper.assignTextViewWithAttr(tv, R.style.style_Dialog_Title)
            return tv
        }
        return null
    }

    private fun configRootLayout(rootView: RUIDialogRootLayout) {

    }

    protected fun onCreateDialogView(context: Context): RUIDialogView {
        var dialogView = RUIDialogView(context)
//        dialogView.setBackgroundColor()
        return dialogView
    }

    protected fun onCreateDialogLayoutParams(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}

