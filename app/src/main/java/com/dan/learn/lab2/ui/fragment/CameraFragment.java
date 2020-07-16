package com.dan.learn.lab2.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.constant.AppConstant;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.utils.FileUtil;
import com.dan.learn.lab2.utils.PermissionUtil;
import com.dan.learn.lab2.utils.camera.CameraHelper;
import com.dan.learn.lab2.utils.camera.CameraSurfaceLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class CameraFragment extends BaseFragment implements CameraHelper.CaptureCallback {

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
    @BindView(R.id.iv_image)
    ImageView iv_image;

    private int mCameraType;
    private CameraHelper mHelper;

    private boolean mInitSurface;

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
        mHelper = new CameraHelper();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_camera;
    }

    @OnClick({R.id.fbt_take_photo, R.id.fbt_init})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbt_init:
                initSurface();
                break;
            case R.id.fbt_take_photo:
                takePhoto();
                break;
        }
    }

    private void takePhoto() {
        switch (mCameraType) {
            case AppConstant.CAMERA_TYPE_CAMERA:
                mHelper.takePhoto(this);
                break;
            case AppConstant.CAMERA_TYPE_CAMERA_2:

                break;
            case AppConstant.CAMERA_TYPE_CAMERA_X:

                break;
        }
    }

    private void initSurface() {
        switch (mCameraType) {
            case AppConstant.CAMERA_TYPE_CAMERA:
                openCamera();
                break;
            case AppConstant.CAMERA_TYPE_CAMERA_2:
                loadImage();
                break;
            case AppConstant.CAMERA_TYPE_CAMERA_X:

                break;
        }
    }

    private void loadImage() {
        String imagePath = FileUtil.getLabDir() + "lab_crop_picture.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        iv_image.setImageBitmap(bitmap);
    }

    private boolean checkAndRequestPermission() {
        boolean success = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] strings = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            int cameraP = PermissionUtil.checkPermission((Activity) mContext, Manifest.permission.CAMERA);
            int cameraSD = PermissionUtil.checkPermission((Activity) mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraSDR = PermissionUtil.checkPermission((Activity) mContext, Manifest.permission.READ_EXTERNAL_STORAGE);

            if (cameraP != PackageManager.PERMISSION_GRANTED
                    || cameraSD != PackageManager.PERMISSION_GRANTED
                    || cameraSDR != PackageManager.PERMISSION_GRANTED) {
                success = false;
                PermissionUtil.requestPermission((Activity) mContext, 100, strings);
            }
        }
        return success;
    }

    private void openCamera() {
        if (checkAndRequestPermission()) {
            if (!mInitSurface) {
                sf_layout.initSurface(mHelper);
                mInitSurface = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            Toast.makeText(mContext, "请求权限失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHelper != null) {
            mHelper.release();
        }
    }

    @Override
    public void onCallback(Bitmap original, Bitmap cropBitmap) {
        ((Activity) mContext).runOnUiThread(() -> {
            mHelper.startPreview();
        });
    }
}
