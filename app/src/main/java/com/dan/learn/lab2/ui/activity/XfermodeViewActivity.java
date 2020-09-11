package com.dan.learn.lab2.ui.activity;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.base.BaseMultiFragmentActivity;
import com.dan.learn.lab2.ui.fragment.XfermodeFragment;

import java.util.List;

public class XfermodeViewActivity extends BaseMultiFragmentActivity {

    @Override
    protected List<BaseFragment> getFragments(List<BaseFragment> fragments) {
        fragments.add(XfermodeFragment.getInstance("变换1", R.layout.avoid_xfermode_layout));
        return fragments;
    }
}
