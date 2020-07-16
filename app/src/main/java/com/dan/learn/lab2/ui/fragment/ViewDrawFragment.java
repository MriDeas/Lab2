package com.dan.learn.lab2.ui.fragment;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;

/**
 * 绘制
 */
public class ViewDrawFragment extends BaseFragment {

    public static ViewDrawFragment getInstance() {
        return new ViewDrawFragment();
    }

    private ViewDrawFragment() {
        super("View 的绘制");
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_view_draw;
    }


}
