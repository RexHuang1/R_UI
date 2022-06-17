package com.dev.rexhuang.rui.dialog;

import android.widget.Button;

import org.jetbrains.annotations.NotNull;

/**
 * *  created by RexHuang
 * *  on 2020/8/22
 */
public class RUIDialogAction {
    @NotNull
    public Button buildActionView(@NotNull RUIDialog dialog, int index) {
        Button button = new Button(dialog.getContext());
        button.setText("test" + index);
        return button;
    }
}
