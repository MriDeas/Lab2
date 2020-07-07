package com.dan.learn.lab2.idle;

import android.widget.TextView;

import androidx.test.espresso.IdlingResource;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.activity.UnitTestActivity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by: dan
 * Created time: 2020/7/7
 * Description:
 * Modify time:
 */
public class SampleIdlingResource implements IdlingResource {

    private ResourceCallback mCallback;
    private AtomicBoolean mIsIdling = new AtomicBoolean(true);
    private UnitTestActivity activity;

    public SampleIdlingResource(UnitTestActivity activity) {
        this.activity = activity;
    }

    @Override
    public String getName() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public boolean isIdleNow() {
        if (activity != null) {
            TextView tvText = activity.findViewById(R.id.tv_test_1);
            String trim = tvText.getText().toString().trim();
            if ("张三的歌".equals(trim)) {
                if (mCallback != null) {
                    mCallback.onTransitionToIdle();
                }
                return true;
            } else {
                return false;
            }
        }
        return mIsIdling.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    public void setIsIdleState(boolean isIdle) {
        mIsIdling.set(isIdle);
        if (mIsIdling.get() && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
