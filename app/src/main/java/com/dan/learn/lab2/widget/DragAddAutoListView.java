package com.dan.learn.lab2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.dan.learn.lab2.R;


/**
 * Created by: dan
 * Created time: 2020/6/8
 * Description:
 * Modify time:
 */
public class DragAddAutoListView extends ListView implements AbsListView.OnScrollListener {

    /**
     * 1. 手动快速向下拉 -- 自动滑动  到达顶部 -> 不允许出现header
     * 2. 手动滑动当滑动header完全呈现  松手后展示header
     */

    private static final String TAG = DragAddAutoListView.class.getCanonicalName();

    private static final float SCROLL_RADIO = 0.35f;
    private static final int MAX_Y_PADDING = 200;
    private static final int MAX_Y_DISTANCE = 720;

    private int mHeaderHeight;

    private View mHeader;

    private int mFirstVisibleItem;
    private float mDownY;
    private float mLastY;

    public DragAddAutoListView(Context context) {
        super(context);
    }

    public DragAddAutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        mHeader = mInflater.inflate(R.layout.header_of_drag_listview, null, false);


        mHeader.measure(0, 0);
        mHeaderHeight = mHeader.getMeasuredHeight();

        addHeaderView(mHeader);
        mHeader.setPadding(0, -MAX_Y_PADDING, 0, 0);
        setOnScrollListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = y - mDownY;
//                LogUtil.d("打印 MOVE 事件 --------- y:" + y + " down y:" + mDownY + " distance:" + distanceY);
                float result;
                if (distanceY > 0 && distanceY <= MAX_Y_DISTANCE && mFirstVisibleItem == 0) {
                    result = (-MAX_Y_PADDING + distanceY) * SCROLL_RADIO;
                    changeHeight((int) result);
                } else if (distanceY < 0 && mFirstVisibleItem == 0) {
                    result = -MAX_Y_PADDING - distanceY;
                    if (result < -MAX_Y_PADDING) {
                        result = -MAX_Y_PADDING;
                    }
                    changeHeight((int) result);
                }

                break;
            case MotionEvent.ACTION_UP:
                float distance = y - mDownY;
//                LogUtil.d("打印UP 参数 ---------- y:" + y + " down y:" + mDownY + " distance:" + distance);
                if (distance > 0) {
                    //向下滑
                    if (distance >= mHeaderHeight * 2) {
                        changeHeight(mHeaderHeight);
//                        if (mListener != null) {
//                            mListener.onDragHappen();
//                        }
                    } else {
                        changeHeight(-mHeaderHeight);
                    }
                } else {
                    //向上
                    changeHeight(-mHeaderHeight);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    private void changeHeight(int height) {
        mHeader.setPadding(0, height, 0, 0);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING) {
            changeHeight(-mHeaderHeight);
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
    }

    public void recoverFromAction() {
        changeHeight(-mHeaderHeight);
    }

    private void whenActionRelease() {
        Toast.makeText(getContext(), "释放", Toast.LENGTH_SHORT).show();
    }

}
