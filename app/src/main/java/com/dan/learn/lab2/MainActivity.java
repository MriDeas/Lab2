package com.dan.learn.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.dan.learn.lab2.adapter.MainFuncAdapter;
import com.dan.learn.lab2.entity.FuncEntity;
import com.dan.learn.lab2.repository.MainDataSet;
import com.dan.learn.lab2.ui.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.elv_expand_list)
    ExpandableListView elv_expand_list;

    private MainFuncAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        MainDataSet mainDataSet = new MainDataSet();
        mainDataSet.initData();
        mAdapter = new MainFuncAdapter(mainDataSet.getData(), this);
        elv_expand_list.setAdapter(mAdapter);
    }

    private void initView() {
        elv_expand_list.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            FuncEntity child = mAdapter.getChild(groupPosition, childPosition);
            navigateTo(child.getTarget());
            return true;
        });
    }

    private void navigateTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
