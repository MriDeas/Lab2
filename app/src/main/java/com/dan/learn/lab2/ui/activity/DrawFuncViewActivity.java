package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;
import com.dan.learn.lab2.widget.view.DrawFunctionView;

import butterknife.BindView;

public class DrawFuncViewActivity extends BaseActivity {

    @BindView(R.id.dfv_view)
    DrawFunctionView dfv_view;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_draw_func_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
