package com.dan.learn.lab2.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;

/**
 * 绘制
 */
public class ViewDrawFragment extends BaseFragment {

    public static ViewDrawFragment getInstance(String title, @LayoutRes int layoutId) {
        return new ViewDrawFragment(title, layoutId);
    }

    private ViewDrawFragment(String title, @LayoutRes int layoutId) {
        super(title);
        mLayoutRes = layoutId;
    }

    private int mLayoutRes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentLayout() {
        return mLayoutRes;
    }


}
