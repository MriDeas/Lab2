package com.dan.learn.lab2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

/**
 * Created by: dan
 * Created time: 2020/6/19
 * Description:
 * Modify time:
 */
public class DragLinearLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;

    public DragLinearLayout(Context context) {
        super(context, null);
    }

    public DragLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mViewDragHelper = ViewDragHelper.create(this,1.0f, new ViewDragHelper.Callback() {
            private int mLeft, mTop;

            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return true;
            }

            @Override
            public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                mLeft = capturedChild.getLeft();
                mTop = capturedChild.getTop();
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                mViewDragHelper.settleCapturedViewAt(0, 100);
                invalidate();
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                mViewDragHelper.settleCapturedViewAt(0, top);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper != null && mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

}
