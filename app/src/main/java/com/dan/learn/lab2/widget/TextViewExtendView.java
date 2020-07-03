package com.dan.learn.lab2.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: dan
 * Created time: 2020/7/3
 * Description:
 * Modify time:
 */
public class TextViewExtendView extends AppCompatTextView {

    private static final int REQ_BINDING_TEXT = 0x1a;

    private Html.ImageGetter mImageGetter;
    private ExecutorService mExecutorService;
    private InnerHandler mHandler;

    public TextViewExtendView(Context context) {
        super(context);
    }

    public TextViewExtendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        getInnerHandler();
    }

    public void setImageGetter(Html.ImageGetter getter) {
        if (getter == null) {
            throw new NullPointerException("param image getter can't be null");
        }
        mImageGetter = getter;
    }

    public Html.ImageGetter getImageGetter() {
        return mImageGetter;
    }

    public void bindText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            setText(text);
        } else {
            parseText(String.valueOf(text));
        }
    }

    private void parseText(String text) {
        execute(() -> {
            Spanned spanned = Html.fromHtml(text, mImageGetter, null);
            Message.obtain(getInnerHandler(), REQ_BINDING_TEXT, spanned).sendToTarget();
        });
    }

    private void execute(Runnable runnable) {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
        mExecutorService.execute(runnable);
    }

    private InnerHandler getInnerHandler() {
        if (mHandler == null) {
            mHandler = new InnerHandler(TextViewExtendView.this);
        }
        return mHandler;
    }

    private static class InnerHandler extends Handler {
        private WeakReference<TextViewExtendView> reference;

        private InnerHandler(TextViewExtendView view) {
            reference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (reference == null) {
                return;
            }
            if (msg.what == REQ_BINDING_TEXT) {
                Spanned span = (Spanned) msg.obj;
                reference.get().setText(span);
            }
        }
    }
}
