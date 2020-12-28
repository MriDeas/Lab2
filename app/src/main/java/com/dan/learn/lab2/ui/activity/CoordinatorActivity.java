package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.refresh.NewsAdapter;
import com.dan.learn.lab2.ui.base.BaseActivity;
import com.dan.learn.lab2.widget.view.refresh.OnRefreshListener;
import com.dan.learn.lab2.widget.view.refresh.views.DividerItemDecoration;
import com.dan.learn.lab2.widget.view.refresh.views.SwipeLayout;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CoordinatorActivity extends BaseActivity implements OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.sp_layout)
    SwipeLayout sp_layout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout app_bar_layout;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rv_recycler_view;

    private List<String> mNewsArray = new ArrayList<>();
    private NewsAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_coordinator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        initView();
        generateData();
    }

    private void initView() {
        sp_layout.setOnRefreshListener(this);
        app_bar_layout.addOnOffsetChangedListener(this);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        rv_recycler_view.addItemDecoration(decoration);
        adapter = new NewsAdapter(mContext);
        rv_recycler_view.setAdapter(adapter);
    }

    private void generateData() {
        for (int i = 0; i < 50; i++) {
            mNewsArray.add("Hello " + i);
        }
        adapter.updateItems(mNewsArray);
    }

    public void stopRefresh(View view) {
        sp_layout.endRefresh();
        sp_layout.endLoadMore();
        sp_layout.noMore(true);
    }

    private boolean isTop() {
        return rv_recycler_view.canScrollVertically(-1);
    }

    private boolean isBottom() {
        return rv_recycler_view.canScrollVertically(1);
    }

    public static boolean isVisBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        return visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE;
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onRefresh() {

        rv_recycler_view.postDelayed(() -> {
            //todo 结束刷新时，不必顾忌当前状态是不允许刷新
            sp_layout.endRefresh();
            sp_layout.endLoadMore();
        }, 2000);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv_recycler_view.getLayoutManager();
        int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();

        if (firstCompletelyVisibleItemPosition == 0) {
            sp_layout.setRefreshEnabled(true);
        } else {
            sp_layout.setRefreshEnabled(false);
        }
    }
}