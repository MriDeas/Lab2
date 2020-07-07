package com.dan.learn.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.dan.learn.lab2.adapter.MainFuncAdapter;
import com.dan.learn.lab2.entity.FuncEntity;
import com.dan.learn.lab2.entity.FuncGroupEntity;
import com.dan.learn.lab2.repository.MainDataSet;
import com.dan.learn.lab2.repository.MainPageRepository;
import com.dan.learn.lab2.ui.RxJavaBasicActivity;
import com.dan.learn.lab2.ui.activity.CustomWidgetsActivity;
import com.dan.learn.lab2.ui.activity.DragViewActivity;
import com.dan.learn.lab2.ui.activity.ThreadActivity;
import com.dan.learn.lab2.ui.activity.UnitTestActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView elv_expand_list;

    private MainFuncAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elv_expand_list = findViewById(R.id.elv_expand_list);
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
