package com.dan.learn.lab2.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import com.dan.learn.lab2.R;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class MiniToDoWidget extends AppWidgetProvider {

    private static final int EVENT_1 = 100;
    private static final int EVENT_2 = 101;
    private static final int EVENT_3 = 102;

    private static final int[] EVENT_ARR = new int[]{EVENT_1, EVENT_2, EVENT_3};

    private AppWidgetManager appWidgetManager;
    private static RemoteViews mRemoteViews;
    private Calendar calendar;
    private Context mContext;

    private TimerHandler mTimerHandler = new TimerHandler(this);

    private static class TimerHandler extends Handler {
        WeakReference<MiniToDoWidget> reference;

        private TimerHandler(MiniToDoWidget widget) {
            reference = new WeakReference<>(widget);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (reference == null || reference.get() == null) {
                return;
            }
            int appWidgetId = (int) msg.obj;
            switch (msg.what) {
                case EVENT_1:
                    Message msg1 = Message.obtain(reference.get().getHandler(), EVENT_1, appWidgetId);
                    sendMessageDelayed(msg1, 3000);
                    break;
                case EVENT_2:
                    Message msg2 = Message.obtain(reference.get().getHandler(), EVENT_2, appWidgetId);
                    sendMessageDelayed(msg2, 3000);
                    break;
                case EVENT_3:
                    Message msg3 = Message.obtain(reference.get().getHandler(), EVENT_3, appWidgetId);
                    sendMessageDelayed(msg3, 3000);
                    break;
            }
        }
    }

    private TimerHandler getHandler() {
        if (mTimerHandler == null) {
            mTimerHandler = new TimerHandler(this);
        }
        return mTimerHandler;
    }

    private void updateTimer(int widgetId) {
        if (mRemoteViews == null) {
            mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.mini_to_do_widget);
        }

        if (appWidgetManager != null) {
            long current = System.currentTimeMillis();
            if (calendar == null) {
                calendar = Calendar.getInstance(Locale.CHINA);
            }
            calendar.setTimeInMillis(current);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int date = calendar.get(Calendar.DAY_OF_MONTH);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            String time = calendar.getTime().toString();
            String dateTime = year + "年" + month + "月" + date + "日";
            String hourTime = hour + ":" + minute + ":" + second;
            dateTime = dateTime + "\n" + hourTime;

            mRemoteViews.setTextViewText(R.id.appwidget_text, dateTime);
            mRemoteViews.setTextViewText(R.id.appwidget_text_2, dateTime);
            mRemoteViews.setTextViewText(R.id.appwidget_text_3, time);

            appWidgetManager.updateAppWidget(widgetId, mRemoteViews);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        mContext = context;
        this.appWidgetManager = appWidgetManager;
        System.out.println("打印信息 onUpdate ------------ ");
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appWidgetId = appWidgetIds[i];
            updateTimer(appWidgetId);
            Message msg = Message.obtain(getHandler());
            msg.obj = appWidgetId;
            msg.what = EVENT_ARR[i];
            getHandler().sendMessage(msg);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        System.out.println("打印信息 onEnabled ------------ ");
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        System.out.println("打印信息 onDisabled ------------ ");
        getHandler().removeCallbacksAndMessages(null);
    }

}

