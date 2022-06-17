package com.dev.rexhuang.rui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.dev.rexhuang.rui.R;

import org.jetbrains.annotations.NotNull;

/**
 * *  created by RexHuang
 * *  on 2020/8/22
 */
public class RUIResHelper {
    private static TypedValue sTmpValue;

    public static int getAttrDimen(Context context, int attrRes) {
        if (sTmpValue == null) {
            sTmpValue = new TypedValue();
        }
        if (!context.getTheme().resolveAttribute(attrRes, sTmpValue, true)) {
            return 0;
        }
        return TypedValue.complexToDimensionPixelSize(sTmpValue.data, RUIDisplayHelper.getDisplayMetrics(context));
    }

    public static void assignTextViewWithAttr(@NotNull TextView textView, @NotNull int styleRes) {
        TypedArray a = textView.getContext().obtainStyledAttributes(styleRes, R.styleable.RUITextCommonStyleDef);
        int count = a.getIndexCount();
        int paddingLeft = textView.getPaddingLeft(), paddingRight = textView.getPaddingRight(),
                paddingTop = textView.getPaddingTop(), paddingBottom = textView.getPaddingBottom();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.RUITextCommonStyleDef_android_gravity) {
                textView.setGravity(a.getInt(attr, -1));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_textColor) {
                textView.setTextColor(a.getColorStateList(attr));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_textSize) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(attr, 0));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_paddingLeft) {
                paddingLeft = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_paddingRight) {
                paddingRight = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_paddingTop) {
                paddingTop = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_paddingBottom) {
                paddingBottom = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_singleLine) {
                textView.setSingleLine(a.getBoolean(attr, false));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_ellipsize) {
                int ellipsize = a.getInt(attr, 3);
                switch (ellipsize) {
                    case 1:
                        textView.setEllipsize(TextUtils.TruncateAt.START);
                        break;
                    case 2:
                        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                        break;
                    case 3:
                        textView.setEllipsize(TextUtils.TruncateAt.END);
                        break;
                    case 4:
                        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                        break;
                }
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_maxLines) {
                textView.setMaxLines(a.getInt(attr, -1));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_background) {

            } else if (attr == R.styleable.RUITextCommonStyleDef_android_lineSpacingExtra) {
                textView.setLineSpacing(a.getDimensionPixelSize(attr, 0), 1f);
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_drawablePadding) {
                textView.setCompoundDrawablePadding(a.getDimensionPixelSize(attr, 0));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_textColorHint) {
                textView.setHintTextColor(a.getColor(attr, -1));
            } else if (attr == R.styleable.RUITextCommonStyleDef_android_textStyle) {
                int styleIndex = a.getInt(attr, -1);
                textView.setTypeface(null, styleIndex);
            }
        }
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        a.recycle();
    }
}
