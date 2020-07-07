package com.dan.learn.lab2.helper;

import android.widget.ExpandableListView;

import androidx.test.espresso.IdlingResource;

/**
 * Created by: dan
 * Created time: 2020/7/6
 * Description:
 * Modify time:
 */
public class ExpandableListViewIdlingResource implements IdlingResource {

    private ResourceCallback callback;
    private ExpandableListView listView;

    public ExpandableListViewIdlingResource(ExpandableListView listView) {
        this.listView = listView;
    }

    @Override
    public String getName() {
        return ExpandableListViewIdlingResource.class.getCanonicalName();
    }

    @Override
    public boolean isIdleNow() {
        if (callback != null) {
            return listView != null && listView.getCount() > 0;
        } else {
            return true;
        }
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
