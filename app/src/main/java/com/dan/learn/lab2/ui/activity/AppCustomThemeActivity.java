package com.dan.learn.lab2.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.dan.learn.lab2.R;

/**
 * 我的主题
 */
public class AppCustomThemeActivity extends Activity {

    private static int night = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (night == 0) {
            setTheme(R.style.DayNightStyle);
        } else if (night == 1) {
            setTheme(R.style.DayLightStyle);
        } else {
            setTheme(R.style.Theme_MaterialComponents_DayNight);
        }

        setContentView(R.layout.activity_app_custom_theme);
    }

    public void switchDay(View v) {
        night = 1;
        recreate();
    }

    public void switchNight(View v) {
        night = 0;
        recreate();
    }

    public void followSystem(View v) {
        night = 2;
        recreate();
    }


}
