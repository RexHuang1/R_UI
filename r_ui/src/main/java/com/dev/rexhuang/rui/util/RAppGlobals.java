package com.dev.rexhuang.rui.util;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

/**
 * *  created by RexHuang
 * *  on 2020/6/12
 */
public class RAppGlobals {

    public static Application getApplication() {
        try {
            return (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
