package com.dan.learn.lab2.ui.base;

import android.os.Bundle;

import com.dan.learn.lab2.R;

public abstract class BaseMultiFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_multi_fragment);
    }



}
