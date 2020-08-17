package com.dan.learn.lab2.utils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.dan.learn.lab2.R;

public class BuildTypeUtil {

    public static void settingActionStyle(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.bac_solid_internal_action_bar));
        }
    }

}
