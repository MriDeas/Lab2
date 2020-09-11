package com.dan.learn.lab2.ui.fragment;

import com.dan.learn.lab2.ui.base.BaseFragment;

public class CanvasFragment extends BaseFragment {

    private int layoutId;

    public CanvasFragment(String title,int layoutId) {
        super(title);
        this.layoutId = layoutId;
    }

    @Override
    protected int getContentLayout() {
        return layoutId;
    }
}
