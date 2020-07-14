package com.dan.learn.lab2.repository;

import com.dan.learn.lab2.entity.FuncGroupEntity;
import com.dan.learn.lab2.ui.RxJavaBasicActivity;
import com.dan.learn.lab2.ui.activity.AccessibilityServiceLabActivity;
import com.dan.learn.lab2.ui.activity.CamerasActivity;
import com.dan.learn.lab2.ui.activity.CustomWidgetsActivity;
import com.dan.learn.lab2.ui.activity.DragViewActivity;
import com.dan.learn.lab2.ui.activity.ThreadActivity;
import com.dan.learn.lab2.ui.activity.UnitTestActivity;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/6
 * Description:
 * Modify time:
 */
public class MainDataSet {

    public void initData() {
        MainPageRepository instance = MainPageRepository.getInstance();
        instance.clear();

        createChild(createGroup("RxJava"), "RxJava基础", "", RxJavaBasicActivity.class);
        createChild(createGroup("自定义控件"), "拖拽控件", "", DragViewActivity.class)
                .addChild("自定义组件", "", CustomWidgetsActivity.class);
        createChild(createGroup("多线程"), "线程Activity", "", ThreadActivity.class);
        createChild(createGroup("单元测试"), "单元测试", "Android 单元测试", UnitTestActivity.class);
        createChild(createGroup("无障碍"), "无障碍服务", "无障碍服务实验室", AccessibilityServiceLabActivity.class);
        createChild(createGroup("相机拍照"), "相机", "相机实验室", CamerasActivity.class);
    }

    public List<FuncGroupEntity> getData() {
        return MainPageRepository.getInstance().getData();
    }

    private FuncGroupEntity createGroup(String title) {
        return MainPageRepository.getInstance().createGroup(title);
    }

    private FuncGroupEntity createChild(FuncGroupEntity parent, String title, String desc, Class clazz) {
        parent.addChild(title, desc, clazz);
        return parent;
    }
}
