package com.dan.learn.lab2.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dan.learn.lab2.R;

/**
 * 我的主题
 */
public class AppCustomThemeActivity extends Activity {

    private static boolean night = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (night) {
            setTheme(R.style.DayNightStyle);
        } else {
            setTheme(R.style.DayLightStyle);
        }
        setContentView(R.layout.activity_app_custom_theme);
    }

    public void switchDay(View v) {
        night = false;
        recreate();
    }

    public void switchNight(View v) {
        night = true;
        recreate();
    }




}
