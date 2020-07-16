package com.dan.learn.lab2.utils.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/14
 * Description:
 * Modify time:
 */
public class CameraSurfaceLayout extends FrameLayout implements SurfaceHolder.Callback, View.OnTouchListener {

    private Camera mCamera;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private List<Camera.Size> mPreviewSizes;
    private CameraHelper helper;
    private LocalAutoFocusCallback mAutoFocusCallback;

    public CameraSurfaceLayout(Context context) {
        super(context, null);
    }

    public CameraSurfaceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initSurface(CameraHelper helper) {
        surfaceView = new SurfaceView(getContext());
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        this.helper = helper;
        setOnTouchListener(this);
    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) {
            return;
        }

        mCamera = camera;
        if (mCamera != null) {
            mPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            requestLayout();
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mCamera.startPreview();
        }
    }

    public SurfaceHolder getHolder() {
        return surfaceView == null ? null : surfaceView.getHolder();
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        helper.openCamera(CameraSurfaceLayout.this, holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(width, height);
        requestLayout();
        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mCamera.autoFocus(null);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            removeCallbacks(null);
            postDelayed(() -> {
                mCamera.autoFocus(getAutoFocusCallback());
            }, 500);
        }
        return true;
    }

    private Camera.AutoFocusCallback getAutoFocusCallback() {
        if (mAutoFocusCallback == null) {
            mAutoFocusCallback = new LocalAutoFocusCallback();
        }
        return mAutoFocusCallback;
    }

    private static class LocalAutoFocusCallback implements Camera.AutoFocusCallback {

        @Override
        public void onAutoFocus(boolean success, Camera camera) {

        }
    }
}
