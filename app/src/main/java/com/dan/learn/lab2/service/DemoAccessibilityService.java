package com.dan.learn.lab2.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/8
 * Description:
 * Modify time:
 */
public class DemoAccessibilityService extends AccessibilityService {

    private AccessibilityServiceInfo info;

    private static boolean mStarted;

    @Override
    public void onCreate() {
        super.onCreate();
        info = new AccessibilityServiceInfo();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        mStarted = true;
        List<CharSequence> text = event.getText();
        int windowId = event.getWindowId();
        AccessibilityNodeInfo source = event.getSource();
        CharSequence sourceText = source.getText();
        CharSequence className = source.getClassName();
        source.getContentDescription();
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                printLog("连接收到Click 事件 --------- text: " + text + " windowId:" + windowId
                        + " sourceText:" + sourceText + " className:" + className + " \nsource: " + source.toString());
                break;
            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                printLog("连接收到Context clicked 事件 --------- " + text + " windowId:" + windowId
                        + " sourceText:" + sourceText + " className:" + className + " \nsource: " + source.toString());
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                printLog("连接收到事件 View Selected --------- " + text + " windowId:" + windowId
                        + " sourceText:" + sourceText + " className:" + className + " \nsource: " + source.toString());
                break;
        }
    }


    @Override
    public void onInterrupt() {
        printLog("连接中断 --------- ");
        mStarted = false;
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        mStarted = true;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.packageNames = new String[]{"com.tencent.mm", "com.mimi.xichelapp"};
        info.notificationTimeout = 100;
        info.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS | AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        setServiceInfo(info);
        printLog("建立连接成功 --------- ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mStarted = false;
        printLog("结束 --------- ");
    }

    private void printLog(String msg) {
        Log.d(DemoAccessibilityService.this.getClass().getCanonicalName(), msg);
    }

    public static boolean isStart() {
        return mStarted;
    }

}
