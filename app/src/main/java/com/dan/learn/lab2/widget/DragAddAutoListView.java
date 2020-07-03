package com.dan.learn.lab2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.utils.SizeUtil;


/**
 * Created by: dan
 * Created time: 2020/6/8
 * Description:
 * Modify time:
 */
public class DragAddAutoListView extends ListView implements AbsListView.OnScrollListener {
    /**
     * 1. 当第一个可见条目是列表中第一个元素时 可向下拖动
     * 2. 设定下拉过程与header部分高度变动的比例，header部分根据比率和滑动实际距离变化
     * 3. UP事件时，判断当前滑动距离
     */

    private static final String TAG = DragAddAutoListView.class.getCanonicalName();
    private static final float SCROLL_RADIO = 0.35f;
    private static int MAX_Y_PADDING;
    private static int MAX_Y_DISTANCE;

    private View mHeader;

    private int mFirstVisibleItem;
    private float mDownY;
    private float mLastY;
    private int mHeaderHeight;

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
        MAX_Y_PADDING = (int) (mHeaderHeight + SizeUtil.dp2px(10));
        MAX_Y_DISTANCE = (int) (MAX_Y_PADDING / SCROLL_RADIO);

        addHeaderView(mHeader);

        int height1 = mHeader.getMeasuredHeight();
        setPadding(0, -mHeaderHeight, 0, 0);
        int height = mHeader.getMeasuredHeight();

        setOnScrollListener(this);
    }

    /**
     * 1. 判断滑动方向
     * 2. 滑动时改变header的padding
     * 3. 判断UP事件时header的 top padding，使用动画
     */

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float distance = y - mDownY;
                if (y >= mLastY) {
                    if (mFirstVisibleItem == 0 && distance <= MAX_Y_DISTANCE) {
                        int padding = (int) ((mHeaderHeight + distance) * SCROLL_RADIO);
                        changeHeight(padding);
                    }
                } else {
                    int topPadding = mHeader.getPaddingTop();
                    if (mFirstVisibleItem == 0) {
                        topPadding--;
                        if (topPadding > -mHeaderHeight) {
                            changeHeight(topPadding);
                        }
                    }
                }

                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (mFirstVisibleItem == 0) {
//                    ViewGroup.LayoutParams params = mHeader.getLayoutParams();
//                    params.height = mHeaderHeight;
//                    mHeader.setLayoutParams(params);
                    changeHeight(0);
                    int height = mHeader.getHeight();
                    int measuredHeight = mHeader.getMeasuredHeight();
//                    setPadding(0,0,0,0);
//                    int paddingTop1 = mHeader.getPaddingTop();

                    Log.d("打印高度 padding ", " ---------  height ：" + height
                            + " measuredHeight :" + measuredHeight);
                }
                mLastY = 0;
                break;
        }
        return super.onTouchEvent(ev);
    }


    private void changeHeight(int height) {
        ViewGroup.LayoutParams params = mHeader.getLayoutParams();
        mHeader.setPadding(0, height, 0, 0);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING) {

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
