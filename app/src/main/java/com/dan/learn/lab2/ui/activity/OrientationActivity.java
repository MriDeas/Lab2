package com.dan.learn.lab2.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;
import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;

public class OrientationActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_orientation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        findViewById(R.id.bt_action).setOnClickListener(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int screenHeightDp = newConfig.screenHeightDp;
        int screenLayout = newConfig.screenLayout;

        System.out.println(" ---------- 打印 配置变化 方向：" + newConfig.orientation + " 布局：" + screenLayout + " Height dp:" + screenHeightDp);
    }

    @Override
    public void onClick(View v) {
        Configuration configuration = Resources.getSystem().getConfiguration();
        System.out.println("打印Window屏幕方向 ---------- ：" + configuration.orientation);
    }
}