package com.dan.learn.lab2.ui.fragment;

import com.dan.learn.lab2.ui.base.BaseFragment;

public class XfermodeFragment extends BaseFragment {

    private int layoutId;
    public static XfermodeFragment getInstance(String title,int layoutId){
        return new XfermodeFragment(title, layoutId);
    }

    protected XfermodeFragment(String title,int layoutId) {
        super(title);
        this.layoutId = layoutId;
    }

    @Override
    protected int getContentLayout() {
        return layoutId;
    }

}
