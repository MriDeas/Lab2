package com.dan.learn.lab2.widget.view.refresh.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.widget.view.refresh.SwipeToLoadLayout;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public class SwipeLayout extends SwipeToLoadLayout {
    public static final int STATE_NORMAL = 0x00;
    public static final int STATE_REFRESH = 0x01;
    public static final int STATE_LOAD_MORE = 0x02;

    public interface ISwipeEndListener {
        void refreshEnd(boolean succ);

        void loadMoreEnd(boolean succ);
    }

    public SwipeLayout(Context context) {
        super(context);
        initView(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.swipe_head, this);
        View.inflate(context, R.layout.swipe_foot, this);
    }

    /**
     * 结束刷新动作
     */
    public void endRefresh() {
        setRefreshing(false);
    }

    /**
     * 结束加载更多动作
     */
    public void endLoadMore() {
        setLoadingMore(false);
    }

    /**
     * 当加载至尾页时调用
     */
    public void noMore(boolean noMore) {
        ((SwipeFootView) findViewById(R.id.swipe_load_more_footer)).noMore(noMore);
    }
}
