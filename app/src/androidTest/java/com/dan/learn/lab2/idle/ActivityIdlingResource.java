package com.dan.learn.lab2.idle;

import android.content.Context;

import androidx.test.espresso.IdlingResource;

import com.dan.learn.lab2.utils.Utils;

/**
 * Created by: dan
 * Created time: 2020/7/7
 * Description:
 * Modify time:
 */
public class ActivityIdlingResource implements IdlingResource {
    private ResourceCallback callback;

    private Context appContext;
    private Class activity;

    public ActivityIdlingResource(Context appContext, Class activity) {
        this.appContext = appContext;
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "ActivityIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        if (callback != null) {
            if (Utils.getTopActivity(appContext).equals(activity.getName())) {
                callback.onTransitionToIdle();
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
