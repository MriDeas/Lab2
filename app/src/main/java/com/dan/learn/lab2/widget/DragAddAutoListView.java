package com.dan.learn.lab2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
                Log.d(TAG, " MOVE -------------- y:" + y + " down y:" + mDownY + " distance :" + distanceY);
                //向下  y 值递增
                //向上  y 值递减

                if (mFirstVisibleItem == 0) {
                    if (y > mLastY) {
                        // 向下
                        if (distanceY > 0 && distanceY <= MAX_Y_DISTANCE) {
                            float height = distanceY * SCROLL_RADIO;
                            Log.d(TAG, "MOVE 向下 height ------------ : " + height);
                            changeHeight((int) height);
                        }
                    } else {
                        //向上
                        //padding 是负值，然后递减 至 MAX_PADDING  - MAX_PADDING --> 0
                        if (distanceY < 0) {
//                            changeHeight((int) distanceY);
                        } else {
                            int paddingTop = mHeader.getPaddingTop();
                            int paddingBottom = mHeader.getPaddingBottom();
                            Log.d(TAG, "打印 --------- Padding top:" + paddingTop + " bottom:" + paddingBottom);

//                            distanceY = MAX_Y_PADDING - (distanceY * SCROLL_RADIO);
//                            float height = MAX_Y_PADDING - ();
//                            changeHeight((int) -distanceY);
                            

                        }

                        Log.d(TAG, "滑动 ----------- distanceY:" + distanceY + " y:" + y + " lastY:" + mLastY);
                    }
                }

                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
//                float distance = y - mDownY;
//                Log.d(TAG, " UP -------------- y:" + y + " down y:" + mDownY + " distance :" + distance);
//
//                if (distance > 0) {
//                    //向下滑
//                    if (distance >= mHeaderHeight * 2) {
//                        changeHeight(mHeaderHeight);
//                    } else {
//                        changeHeight(-mHeaderHeight);
//                    }
//                } else {
//                    //向上
//                    changeHeight(-mHeaderHeight);
//                }
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

    private void whenActionRelease() {
        Toast.makeText(getContext(), "释放", Toast.LENGTH_SHORT).show();
    }

}
