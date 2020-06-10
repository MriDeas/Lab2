package com.dan.learn.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.dan.learn.lab2.adapter.MainFuncAdapter;
import com.dan.learn.lab2.entity.FuncEntity;
import com.dan.learn.lab2.entity.FuncGroupEntity;
import com.dan.learn.lab2.ui.RxJavaBasicActivity;

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
        FuncEntity child = new FuncEntity();
        child.setName("RxJava 基础使用");
        child.setTarget(RxJavaBasicActivity.class);

        ArrayList<FuncEntity> entities = new ArrayList<>();
        entities.add(child);
        FuncGroupEntity group = new FuncGroupEntity();
        group.setTitle("RxJava");
        group.setChildren(entities);
        ArrayList<FuncGroupEntity> groups = new ArrayList<>();
        groups.add(group);
        mAdapter = new MainFuncAdapter(groups, this);
        elv_expand_list.setAdapter(mAdapter);
    }

    private void initView() {
        elv_expand_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FuncEntity child = mAdapter.getChild(groupPosition, childPosition);
                navigateTo(child.getTarget());
                return true;
            }
        });
    }

    private void navigateTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
