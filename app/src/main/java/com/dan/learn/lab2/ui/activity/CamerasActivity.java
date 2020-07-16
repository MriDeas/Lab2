package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.FragmentsAdapter;
import com.dan.learn.lab2.constant.AppConstant;
import com.dan.learn.lab2.ui.BaseActivity;
import com.dan.learn.lab2.ui.BaseFragment;
import com.dan.learn.lab2.ui.fragment.CameraFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 相机
 */
public class CamerasActivity extends BaseActivity {


    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.vp_cameras)
    ViewPager vp_cameras;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("相机");
        initView();
    }

    private void initView() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(CameraFragment.getInstance(AppConstant.CAMERA_TYPE_CAMERA, "普通相机"));
        fragments.add(CameraFragment.getInstance(AppConstant.CAMERA_TYPE_CAMERA_2, "Camera 2"));
        fragments.add(CameraFragment.getInstance(AppConstant.CAMERA_TYPE_CAMERA_X, "Camera X"));
        tab_layout.setupWithViewPager(vp_cameras);

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager(), fragments);
        vp_cameras.setAdapter(adapter);
        vp_cameras.setOffscreenPageLimit(3);
    }


}
