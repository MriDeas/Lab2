package com.dan.learn.lab2.ui.activity;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.base.BaseMultiFragmentActivity;
import com.dan.learn.lab2.ui.fragment.ViewDemoFragment;

import java.util.List;

public class ViewDemoActivity extends BaseMultiFragmentActivity {

    @Override
    protected List<BaseFragment> getFragments(List<BaseFragment> fragments) {
        fragments.add(new ViewDemoFragment("TabLayout", R.layout.fragment_tab_layout));
        return fragments;
    }

}
