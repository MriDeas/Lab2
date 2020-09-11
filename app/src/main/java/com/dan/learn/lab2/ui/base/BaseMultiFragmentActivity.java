package com.dan.learn.lab2.ui.base;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.FragmentsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BaseMultiFragmentActivity extends BaseActivity {

    @BindView(R.id.vp_pager)
    ViewPager vp_pager;
    @BindView(R.id.tl_tab_view)
    TabLayout tl_tab_view;

    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_base_multi_fragment;
    }

    protected abstract List<BaseFragment> getFragments(List<BaseFragment> fragments);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vp_pager.setOffscreenPageLimit(3);
        tl_tab_view.setupWithViewPager(vp_pager);

        vp_pager.setAdapter(new FragmentsAdapter(getSupportFragmentManager(), getFragments(fragments)));
    }


}
