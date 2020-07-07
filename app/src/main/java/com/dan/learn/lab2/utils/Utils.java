package com.dan.learn.lab2.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/7
 * Description:
 * Modify time:
 */
public class Utils {
    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity).getClassName();
        } else {
            return null;
        }
    }
}
