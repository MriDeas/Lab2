package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

public class SimpleViewPager extends ViewGroup {

    private VelocityTracker velocityTracker;
    private float downX;
    private final ViewConfiguration mViewConfiguration;
    private boolean isScrolling;
    private OverScroller mScroller;
    private float maxXVelocity;
    private float minXVelocity;
    private int downScrollX;

    public SimpleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        velocityTracker = VelocityTracker.obtain();
        mViewConfiguration = ViewConfiguration.get(context);
        mScroller = new OverScroller(context);
        maxXVelocity = mViewConfiguration.getScaledMaximumFlingVelocity();
        minXVelocity = mViewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        int right = getWidth();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(left, 0, right, getHeight());
                left += getWidth();
                right += getWidth();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        boolean result = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downScrollX = getScrollX();
                isScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float dX = downX - ev.getX();
                System.out.println("打印 dX ------------ " + dX);
                if (!isScrolling) {
                    if (Math.abs(dX) > mViewConfiguration.getScaledPagingTouchSlop()) {
                        isScrolling = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        result = true;
                    }
                }
                break;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear();
        }
        velocityTracker.addMovement(event);
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                isScrolling = false;
                downScrollX = getScrollX(); //scrollX 是View左边界滑动的距离，向左变大，向右变小. 有负值
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.getXVelocity();
                float dX = downX - x + downScrollX;
                if (dX < 0) {
                    dX = 0;
                } else if (dX > getWidth()) {
                    dX = getWidth();
                }
                scrollTo((int) dX, 0);
                break;
            case MotionEvent.ACTION_UP:
                //计算速度
                //判断速度 和 移动方向， 速度大于某常量时，开始向一边位移
                velocityTracker.computeCurrentVelocity(1000, maxXVelocity);
                float xV = velocityTracker.getXVelocity();
                int scrollX = getScrollX();
                int targetPage;
                if (Math.abs(xV) < minXVelocity) {
                    // 滑动距离大于View宽度一半时，页面翻新，否则到原页面
                    targetPage = scrollX > getWidth() / 2 ? 1 : 0;
                } else {
                    // 速度 小于0，表示向左，否则向右
                    targetPage = xV < 0 ? 1 : 0;
                }
                //距离根据页面判断
                int scrollDistance = targetPage == 1 ? getWidth() - scrollX : -scrollX;
                mScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
                postInvalidateOnAnimation(); //下一帧执行时（延迟）会调用invalidate()，然后执行 draw -> computeScroll
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidateOnAnimation();
        }
    }
}
