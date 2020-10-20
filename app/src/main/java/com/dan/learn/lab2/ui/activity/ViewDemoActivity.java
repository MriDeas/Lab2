package com.dan.learn.lab2.ui.activity;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.base.BaseMultiFragmentActivity;
import com.dan.learn.lab2.ui.fragment.ViewDemoFragment;

import java.util.List;

public class ViewDemoActivity extends BaseActivity {
//BaseMultiFragmentActivity
//    @Override
//    protected List<BaseFragment> getFragments(List<BaseFragment> fragments) {
////        fragments.add(new ViewDemoFragment("Tab_Layout", R.layout.fragment_tab_layout));
////        fragments.add(new ViewDemoFragment("Touch_View", R.layout.fragment_touch_layout));
//        fragments.add(new ViewDemoFragment("View_Pager", R.layout.fragment_view_pager));
//        return fragments;
//    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_view_pager;
    }
}
