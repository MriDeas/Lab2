package com.dan.learn.lab2.repository;

import com.dan.learn.lab2.entity.FuncGroupEntity;
import com.dan.learn.lab2.ui.activity.AccessibilityServiceLabActivity;
import com.dan.learn.lab2.ui.activity.AnnotationActivity;
import com.dan.learn.lab2.ui.activity.AppCustomThemeActivity;
import com.dan.learn.lab2.ui.activity.BinderActivity;
import com.dan.learn.lab2.ui.activity.CamerasActivity;
import com.dan.learn.lab2.ui.activity.CanvasActivity;
import com.dan.learn.lab2.ui.activity.CustomWidgetsActivity;
import com.dan.learn.lab2.ui.activity.DragViewActivity;
import com.dan.learn.lab2.ui.activity.DrawFuncViewActivity;
import com.dan.learn.lab2.ui.activity.RxJavaBasicActivity;
import com.dan.learn.lab2.ui.activity.ThreadActivity;
import com.dan.learn.lab2.ui.activity.UnitTestActivity;
import com.dan.learn.lab2.ui.activity.ViewDemoActivity;
import com.dan.learn.lab2.ui.activity.ViewKnowledgeActivity;
import com.dan.learn.lab2.ui.activity.ViewPathActivity;
import com.dan.learn.lab2.ui.activity.XfermodeViewActivity;

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

        createDatabase();
        createViewKnowledge();
        createCustomView();
        createMultiThread();
        createRxJavaData();
        createCamera();
        createAccessibility();
        createAnnotation();
        createUnitTest();
        createAppTheme();
        createBinder();
    }

    private void createBinder() {
        FuncGroupEntity binder = makeGroup("Binder手写");
        binder.addChild("手写binder", "代替AIDL", BinderActivity.class);
    }

    private void createViewKnowledge() {
        FuncGroupEntity view = makeGroup("View基础");
        view.addChild("View 基础 Paint", "View 画笔", ViewKnowledgeActivity.class);
        view.addChild("View 基础 Path", "View Path", ViewPathActivity.class);
        view.addChild("View 基础 Xfermode", "View Xfermode", XfermodeViewActivity.class);
        view.addChild("View 基础 Canvas", "View Canvas", CanvasActivity.class);
    }

    private void createRxJavaData() {
        FuncGroupEntity rxJava = makeGroup("RxJava");
        rxJava.addChild("RxJava基础", "RxJava", RxJavaBasicActivity.class);
    }

    private void createCustomView() {
        FuncGroupEntity viewKnowledge = makeGroup("自定义控件");
        viewKnowledge.addChild("拖拽控件", "拖拽", DragViewActivity.class);
        viewKnowledge.addChild("自定义组件", "其他组件", CustomWidgetsActivity.class);
        viewKnowledge.addChild("自定义控件", "View 绘制系列", DrawFuncViewActivity.class);
        viewKnowledge.addChild("自定义控件", "View Demo ", ViewDemoActivity.class);
    }

    private void createDatabase() {
        FuncGroupEntity database = makeGroup("数据库");
        database.addChild("SQLite 数据库", "数据库", null);
        database.addChild("Room 数据库", "官方支持的 Room 数据库", null);
    }

    private void createMultiThread() {
        FuncGroupEntity multiThread = makeGroup("多线程");
        multiThread.addChild("线程Activity", "多线程", ThreadActivity.class);
    }

    private void createUnitTest() {
        FuncGroupEntity unitTest = makeGroup("单元测试");
        unitTest.addChild("单元测试", "Android 单元测试", UnitTestActivity.class);
    }

    private void createAccessibility() {
        FuncGroupEntity accessibility = makeGroup("无障碍");
        accessibility.addChild("无障碍服务", "无障碍服务实验室", AccessibilityServiceLabActivity.class);
    }

    private void createCamera() {
        FuncGroupEntity camera = makeGroup("相机拍照");
        camera.addChild("相机", "相机实验室", CamerasActivity.class);
    }

    private void createAnnotation() {
        FuncGroupEntity annotation = makeGroup("注解");
        annotation.addChild("注解", "define and use annotation", AnnotationActivity.class);
    }

    private void createAppTheme() {
        FuncGroupEntity view = makeGroup("主题");
        view.addChild("自定义主题", "定义我的主题", AppCustomThemeActivity.class);
    }

    public List<FuncGroupEntity> getData() {
        return MainPageRepository.getInstance().getData();
    }

    private FuncGroupEntity makeGroup(String title) {
        return MainPageRepository.getInstance().createGroup(title);
    }
}
