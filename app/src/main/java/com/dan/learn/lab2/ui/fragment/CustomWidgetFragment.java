package com.dan.learn.lab2.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.BaseFragment;

public class CustomWidgetFragment extends BaseFragment {

    private CustomWidgetFragment(String title) {
        super(title);
    }

    public static CustomWidgetFragment getInstance(String title) {
        return new CustomWidgetFragment(title);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_custom_widget;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){

    }

}
