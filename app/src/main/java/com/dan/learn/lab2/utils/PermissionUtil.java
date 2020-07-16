package com.dan.learn.lab2.utils;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by: dan
 * Created time: 2020/7/14
 * Description:
 * Modify time:
 */
public class PermissionUtil {


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity, String permission, int reqCode) {
        activity.requestPermissions(new String[]{permission}, reqCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity, int reqCode, String... permissions) {
        activity.requestPermissions(permissions, reqCode);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int checkPermission(Activity activity, String permission) {
        return activity.checkSelfPermission(permission);
    }


}
