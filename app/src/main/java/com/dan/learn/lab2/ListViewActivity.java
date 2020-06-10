package com.dan.learn.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.dan.learn.lab2.adapter.DragListAdapter;
import com.dan.learn.lab2.widget.DragAddAutoListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private DragAddAutoListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.list_view);
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                Log.d("onScrollStateChanged ", " --------------- scrollState: " + scrollState);
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.d("ListViewActivity -> ", "--------------- msg firstVisibleItem: " + firstVisibleItem + " visibleItemCount:"
//                        + visibleItemCount + " totalItemCount：" + totalItemCount);
//            }
//        });
//
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            String content = "Item : " + i;
            list.add(content);
        }
        bindingAdapter(list);
//
//        mListView.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                if (v instanceof TextView) {
//                    CharSequence text = ((TextView) v).getText();
//                    Log.d("onDrag", "drag ---------- " + text);
//                }
//                return false;
//            }
//        });
//
//        mListView.setRecyclerListener(new AbsListView.RecyclerListener() {
//            @Override
//            public void onMovedToScrapHeap(View view) {
//                if (view instanceof TextView) {
//                    CharSequence text = ((TextView) view).getText();
//                    Log.d("onMovedToScrapHeap", "drag ---------- " + text);
//                }
//            }
//        });
//
//        mListView.setOnHoverListener(new View.OnHoverListener() {
//            @Override
//            public boolean onHover(View v, MotionEvent event) {
//                if (v instanceof TextView) {
//                    CharSequence text = ((TextView) v).getText();
//                    Log.d("onHover", "drag ---------- " + text);
//                }
//                return false;
//            }
//        });
    }


    private void bindingAdapter(List<String> data) {
        DragListAdapter adapter = new DragListAdapter(data, this);
        mListView.setAdapter(adapter);
    }


}
