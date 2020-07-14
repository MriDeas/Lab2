package com.dan.learn.lab2.ui.fragment;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.constant.AppConstant;
import com.dan.learn.lab2.ui.BaseFragment;
import com.dan.learn.lab2.utils.camera.CameraHelper;
import com.dan.learn.lab2.utils.camera.CameraSurfaceLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class CameraFragment extends BaseFragment {

    private static final String ARGUMENT_CAMERA_TYPE = "camera_type";

    public static CameraFragment getInstance(@IntRange(from = 1, to = 3) int cameraType, String title) {
        CameraFragment fragment = new CameraFragment(title);
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_CAMERA_TYPE, cameraType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.sf_layout)
    CameraSurfaceLayout sf_layout;

    private int mCameraType;
    private CameraHelper mHelper;


    private CameraFragment(String title) {
        super(title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCameraType = arguments.getInt(ARGUMENT_CAMERA_TYPE);
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_camera;
    }

    @OnClick(R.id.fbt_take_photo)
    void onClick(View v) {
        Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
        switch (mCameraType) {
            case AppConstant.CAMERA_TYPE_CAMERA:
                openCamera();
                break;
            case AppConstant.CAMERA_TYPE_CAMERA_2:

                break;
            case AppConstant.CAMERA_TYPE_CAMERA_X:

                break;
        }
    }


    private void openCamera() {
        if (mHelper == null) {
            mHelper = new CameraHelper();
        }

        mHelper.setPreview(sf_layout);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.release();
        }
    }
}
