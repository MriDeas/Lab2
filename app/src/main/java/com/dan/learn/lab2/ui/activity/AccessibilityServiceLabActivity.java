package com.dan.learn.lab2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.service.DemoAccessibilityService;
import com.dan.learn.lab2.ui.BaseActivity;

import butterknife.OnClick;

/**
 * 无障碍服务
 */
public class AccessibilityServiceLabActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_accessibility_service_lab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @OnClick(R.id.bt_start)
    public void onClick(View v) {
        if (!DemoAccessibilityService.isStart()) {
            try {
                startService(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } catch (Exception e) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        }
    }

}
