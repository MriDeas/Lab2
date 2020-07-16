package com.dan.learn.lab2.utils.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.widget.Toast;

import androidx.annotation.IntRange;

import com.dan.learn.lab2.utils.FileUtil;
import com.dan.learn.lab2.utils.SizeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by: dan
 * Created time: 2020/7/14
 * Description:
 * Modify time:
 */
public class CameraHelper {

    private CameraSurfaceLayout mPreview;
    private Camera camera;

    private boolean safelyOpen() {

        boolean success = false;
        try {
            camera = Camera.open();
            success = camera != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    void openCamera(CameraSurfaceLayout preview, SurfaceHolder holder) {
        mPreview = preview;
        boolean b = safelyOpen();
        if (b) {
            initParameters(holder, 90);
            preview.setCamera(camera);
        }
    }


    public void setPreview(CameraSurfaceLayout preview) {
        mPreview = preview;
        if (mPreview != null) {
            boolean success = safelyOpen();
            if (success) {
                initParameters(preview.getHolder(), 90);
                mPreview.setCamera(camera);
            }
        }
    }

    public void changeDisplayOrientation(@IntRange(from = 0, to = 360) int degree) {
        camera.setDisplayOrientation(degree);
    }

    public void takePhoto(CaptureCallback callback) {
        camera.takePicture(() -> {
            showTip("快门准备，开始拍照");
        }, null, (data, camera) -> {
            showTip("加工后的图");
            parseByteData(data, callback);
        });
    }

    public void startPreview() {
        if (camera != null) {
            camera.startPreview();
            camera.autoFocus(null);
        }
    }

    private void parseByteData(byte[] data, CaptureCallback callback) {
        new Thread(() -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (callback != null) {
                cache(bitmap, "lab_crop_picture");
                callback.onCallback(bitmap, null);
            }
        }).start();
    }

    private void initParameters(SurfaceHolder holder, @IntRange(from = 0, to = 360) int degree) {
        Camera.Parameters parameters = camera.getParameters();

        camera.setDisplayOrientation(degree);
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        parameters.set("jpeg-quality", 100);
        parameters.setPreviewFormat(PixelFormat.JPEG);
        parameters.setPictureFormat(PixelFormat.JPEG);
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        parameters.setPreviewSize(((int) SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_WIDTH)),
                ((int) (SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_HEIGHT))));
        try {
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        if (mPreview != null) {
            mPreview.release();
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private void cache(Bitmap bitmap, String name) {
        if (bitmap == null) return;
        try {
            String s = FileUtil.getLabDir() + name + ".jpg";
            FileOutputStream out = new FileOutputStream(new File(s));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            printBitmapInfo(bitmap, name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printBitmapInfo(Bitmap bitmap, String name) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int byteCount = bitmap.getByteCount();
        Bitmap.Config config = bitmap.getConfig();
        int density = bitmap.getDensity();

        String builder = " height : " + height +
                " width:" + width +
                " byteCount:" + byteCount +
                " config:" + config +
                " density:" + density;
        System.out.println("打印Bitmap 信息 ------- name:" + name + builder);
    }

    private void showTip(String msg) {
        Toast.makeText(mPreview.getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    public interface CaptureCallback {
        void onCallback(Bitmap original, Bitmap cropBitmap);
    }

}
