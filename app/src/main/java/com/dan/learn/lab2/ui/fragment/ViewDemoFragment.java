package com.dan.learn.lab2.ui.fragment;

import com.dan.learn.lab2.ui.base.BaseFragment;

public class ViewDemoFragment extends BaseFragment {

    private int layoutRes;

    public ViewDemoFragment(String title, int layoutId) {
        super(title);
        this.layoutRes = layoutId;
    }

    @Override
    protected int getContentLayout() {
        return layoutRes;
    }


}
