package com.dan.learn.lab2.utils.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/14
 * Description:
 * Modify time:
 */
public class CameraSurfaceLayout extends ViewGroup implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private List<Camera.Size> mPreviewSizes;

    public CameraSurfaceLayout(Context context) {
        super(context, null);
    }

    public CameraSurfaceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceView = new SurfaceView(context, attrs);
        addView(surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) {
            return;
        }

        mCamera = camera;
        if (mCamera != null) {
            List<Camera.Size> localSize = mCamera.getParameters().getSupportedPreviewSizes();
            mPreviewSizes = localSize;
            requestLayout();
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mCamera.startPreview();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(width, height);
        requestLayout();
        mCamera.setParameters(parameters);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        holder.removeCallback(this);
    }

    public void release() {
        if (holder != null) {
            holder.removeCallback(this);
        }

        surfaceView = null;
        removeAllViews();
    }
}
