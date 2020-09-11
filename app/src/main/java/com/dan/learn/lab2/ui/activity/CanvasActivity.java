package com.dan.learn.lab2.ui.activity;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.base.BaseMultiFragmentActivity;
import com.dan.learn.lab2.ui.fragment.CanvasFragment;

import java.util.List;

public class CanvasActivity extends BaseMultiFragmentActivity {

    @Override
    protected List<BaseFragment> getFragments(List<BaseFragment> fragments) {
        fragments.add(new CanvasFragment("画板1", R.layout.fragment_canvas));
        return fragments;
    }
}
