package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.FragmentsAdapter;
import com.dan.learn.lab2.ui.base.BaseActivity;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.fragment.ViewDrawFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewPathActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.vp_views)
    ViewPager vp_views;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_view_path;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Path");

        initView();
    }

    private void initView() {
        tab_layout.setupWithViewPager(vp_views);
        vp_views.setOffscreenPageLimit(3);
        List<BaseFragment> list = new ArrayList<>();
        list.add(ViewDrawFragment.getInstance("🖌 Path", R.layout.fragment_path));

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager(), list);
        vp_views.setAdapter(adapter);
    }

}
