package com.dan.learn.lab2.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by: dan
 * Created time: 2020/7/15
 * Description:
 * Modify time:
 */
public class FileUtil {

    public static String getRootDir() {
        if (ExistSDCard()) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        } else {
            return "";
        }
    }

    public static String getLabDir() {
        File file = new File(getRootDir() + "");
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
        }
        return getRootDir() + "/";
    }

    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else return false;
    }
}
