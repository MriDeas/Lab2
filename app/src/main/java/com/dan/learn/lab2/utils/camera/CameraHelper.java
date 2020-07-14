package com.dan.learn.lab2.utils.camera;

import android.hardware.Camera;

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

    public void setPreview(CameraSurfaceLayout preview) {
        mPreview = preview;
        if (mPreview != null) {
            boolean success = safelyOpen();
            if (success) {
                mPreview.setCamera(camera);
            }
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

}
