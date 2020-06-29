package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.FragmentsAdapter;
import com.dan.learn.lab2.ui.BaseActivity;
import com.dan.learn.lab2.ui.BaseFragment;
import com.dan.learn.lab2.ui.fragment.CustomWidgetFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 自定义控件
 */

public class CustomWidgetsActivity extends BaseActivity {

    @BindView(R.id.tl_vp_tab)
    TabLayout tl_vp_tab;
    @BindView(R.id.vp_pager)
    ViewPager vp_pager;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_widget;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("自定义控件");
        initView();
    }

    private void initView() {
        tl_vp_tab.setupWithViewPager(vp_pager);
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(CustomWidgetFragment.getInstance("Little Widget"));
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager(), fragments);
        vp_pager.setAdapter(adapter);
    }

}
