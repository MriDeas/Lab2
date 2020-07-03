package com.dan.learn.lab2.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.utils.SizeUtil;

/**
 * Created by: dan
 * Created time: 2020/7/1
 * Description:
 * Modify time:
 */
public class DragListView extends ListView implements AbsListView.OnScrollListener {

    int STATE_NORMAL = 0;
    int STATE_REFRESH_RELEASE = 1;
    int STATE_REFRESHING = 2;
    int STATE_REFRESH_COMPLETE = 3;

    private float mLastY;
    private float mDeltaY;

    private int currentState;
    private View mHeader;
    private int mFirstVisibleItem;
    private int mMeasuredHeight;

    public DragListView(Context context) {
        super(context);
    }

    public DragListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        mHeader = mInflater.inflate(R.layout.header_of_drag_listview, null, false);
        mMeasuredHeight = (int) SizeUtil.dp2px(175);
//        mHeader.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        mHeader.setLayoutParams(params);
        addHeaderView(mHeader);
        setPadding(0, 0, 0, 0);
        mHeader.setVisibility(GONE);

        setOnScrollListener(this);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (mLastY == -1) {
//            mLastY = ev.getRawY();
//        }
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastY = ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mDeltaY = ev.getRawY() - mLastY;
//                mLastY = ev.getRawY();
//                //从屏幕上方到下方 y值 逐渐增大
//                if (isOnTop()) {
//                    onMove(mDeltaY / 3);
//                }
//
//                if (getVisibleHeight() > 0 && currentState < STATE_REFRESHING) {
//                    return false;
//                }
//                break;
//            default:
//                mLastY = -1;
//                if (isOnTop()) {
//                    refreshComplete();
//                }
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//    private void refreshComplete() {
//        setState(STATE_REFRESH_COMPLETE);
//        smoothScrollTo(0);
//    }
//
//    private boolean isOnTop() {
//        return mFirstVisibleItem == 0;
//    }
//
//    private void onMove(float distance) {
//        if (getVisibleHeight() > 0 || distance > 0) {
//            setVisibleHeight((int) (getVisibleHeight() + distance));
//            if (getVisibleHeight() > mMeasuredHeight) {
//                setState(STATE_REFRESH_RELEASE);
//            } else {
//                setState(STATE_NORMAL);
//            }
//        }
//    }
//
//    private void setState(int state) {
//        if (state == currentState) return;
//        currentState = state;
//        if (state == STATE_REFRESHING) {
//            smoothScrollTo(mMeasuredHeight);
//        }
//    }
//
//    private float getVisibleHeight() {
//        return mHeader.getLayoutParams().height;
//    }
//
//    private void setVisibleHeight(int height) {
//        if (height < 0) height = 0;
//        AbsListView.LayoutParams params = (LayoutParams) mHeader.getLayoutParams();
//        params.height = height;
//        mHeader.setLayoutParams(params);
//    }
//
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
    }
//
//    private void smoothScrollTo(int des) {
//        float visibleHeight = getVisibleHeight();
//        ValueAnimator animator = ValueAnimator.ofInt((int) visibleHeight, des);
//        animator.setDuration(300);
//        animator.addUpdateListener(animation -> setVisibleHeight((Integer) animation.getAnimatedValue()));
//        animator.start();
//    }
}
