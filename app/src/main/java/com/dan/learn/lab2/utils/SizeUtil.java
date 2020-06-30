package com.dan.learn.lab2.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.annotation.IntRange;

/**
 * Created by: dan
 * Created time: 2020/6/30
 * Description:
 * Modify time:
 */
public class SizeUtil {

    public static final int COMMAND_SCREEN_WIDTH = 0;
    public static final int COMMAND_SCREEN_HEIGHT = 1;
    public static final int COMMAND_SCREEN_DENSITY = 2;
    public static final int COMMAND_SCREEN_SCALE_DENSITY = 3;
    public static final int COMMAND_SCREEN_DENSITY_DPI = 4;
    public static final int COMMAND_X_DPI = 5;
    public static final int COMMAND_Y_DPI = 6;

    public static float getScreenParam(@IntRange(from = 0, to = 6) int command) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        if (command == COMMAND_SCREEN_WIDTH) {
            return displayMetrics.widthPixels;
        } else if (command == COMMAND_SCREEN_HEIGHT) {
            return displayMetrics.heightPixels;
        } else if (command == COMMAND_SCREEN_DENSITY) {
            return displayMetrics.density;
        } else if (command == COMMAND_SCREEN_DENSITY_DPI) {
            return displayMetrics.densityDpi;
        } else if (command == COMMAND_SCREEN_SCALE_DENSITY) {
            return displayMetrics.scaledDensity;
        } else if (command == COMMAND_X_DPI) {
            return displayMetrics.xdpi;
        } else if (command == COMMAND_Y_DPI) {
            return displayMetrics.ydpi;
        }
        return 0;
    }

    public static float dp2px(float value) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return value * displayMetrics.density + 0.5f;
    }

}
