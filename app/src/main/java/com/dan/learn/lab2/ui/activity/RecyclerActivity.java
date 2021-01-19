package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.SampleRecyclerAdapter;
import com.dan.learn.lab2.entity.DiffEntity;
import com.dan.learn.lab2.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;

public class RecyclerActivity extends BaseActivity {

    @BindView(R.id.rv_recycler_view)
    RecyclerView rv_recycler_view;
    @BindView(R.id.bt_refresh)
    View bt_refresh;
    @BindView(R.id.bt_refresh_2)
    View bt_refresh_2;
    @BindView(R.id.bt_refresh_3)
    View bt_refresh_3;
    @BindView(R.id.bt_refresh_4)
    View bt_refresh_4;

    private SampleRecyclerAdapter adapter;
    private List<DiffEntity> list;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
            diffResult.dispatchUpdatesTo(adapter);
            adapter.setData(newData);
            return true;
        }
    });
    private ExecutorService executorService;
    private List<DiffEntity> newData;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        // 刷新单条数据
        // 新增数据
        // 删除数据
        // 优化后效果计算
        bt_refresh.setOnClickListener(v -> {
            refreshItemData();
        });
        bt_refresh_2.setOnClickListener(v -> {
            refreshPartsData();
        });
        bt_refresh_3.setOnClickListener(v -> {
            deleteFirst();
        });
        bt_refresh_4.setOnClickListener(v -> {
            deleteLast();
        });
    }

    private void initData() {
        List<DiffEntity> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DiffEntity entity = new DiffEntity();
            entity.setTitle("标题" + i);
            entity.setDesc("描述" + i);
            entity.setState("State：" + i);
            data.add(entity);
        }
        list = data;
        bindingAdapter(data);
    }

    private void refreshPartsData() {
        newData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DiffEntity entity = new DiffEntity();
            entity.setTitle("标题" + i);
            entity.setDesc("描述" + i);
            entity.setState("State：" + i);
            newData.add(entity);
        }
        Random random = new Random();
        DiffEntity diffEntity = newData.get(9);
        diffEntity.setDesc("修改了内容:" + random.nextInt(20));
        diffEntity.setState("modify：" + 9);

        bindingAdapter(newData);
    }

    private void refreshItemData() {
        newData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DiffEntity entity = new DiffEntity();
            entity.setTitle("标题" + i);
            entity.setDesc("描述" + i);
            entity.setState("State：" + i);
            newData.add(entity);
        }
        Random random = new Random();
        DiffEntity diffEntity = newData.get(9);
        diffEntity.setTitle("修改标题");
        diffEntity.setDesc("标题改变了" + random.nextInt(20));
        diffEntity.setState("item change");

        bindingAdapter(newData);
    }

    private void deleteFirst() {
//        if (!list.isEmpty()) {
//            list.remove(0);
//        }
//        bindingAdapter(newData);
    }

    private void deleteLast() {
//        if (!list.isEmpty()) {
//            list.remove(list.size() - 1);
//        }
//        bindingAdapter(newData);
    }

    private void calculateDiff(List<DiffEntity> newData) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(2);
        }
        executorService.submit(() -> {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(newData, list));
            Message msg = Message.obtain();
            msg.obj = diffResult;
            handler.sendMessage(msg);
        });
    }

    private void bindingAdapter(List<DiffEntity> n) {
        if (adapter == null) {
            adapter = new SampleRecyclerAdapter(this, list);
            rv_recycler_view.setAdapter(adapter);
        } else {
            calculateDiff(n);
        }
    }


    private static class DiffUtilCallback extends DiffUtil.Callback {

        private final List<DiffEntity> newData;
        private final List<DiffEntity> oldData;

        public DiffUtilCallback(List<DiffEntity> newData, List<DiffEntity> oldData) {
            this.newData = newData;
            this.oldData = oldData;
        }

        @Override
        public int getOldListSize() {
            return oldData == null ? 0 : oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData == null ? 0 : newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            DiffEntity oldData = this.oldData.get(oldItemPosition);
            DiffEntity newData = this.newData.get(newItemPosition);
            boolean equals = oldData.getTitle().equals(newData.getTitle());
            Log.d("打印item is same:", "equals:" + equals + " newItemPosition:" + newItemPosition);
            return equals;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            DiffEntity oldData = this.oldData.get(oldItemPosition);
            DiffEntity newData = this.newData.get(newItemPosition);
            boolean isSame = true;
            if (!oldData.getDesc().equals(newData.getDesc())) {
                isSame = false;
            }
            if (!oldData.getState().equals(newData.getState())) {
                isSame = false;
            }
            if (!oldData.getTitle().equals(newData.getTitle())) {
                isSame = false;
            }

            return isSame;
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            DiffEntity oldData = this.oldData.get(oldItemPosition);
            DiffEntity newData = this.newData.get(newItemPosition);
            Bundle bundle = new Bundle();
            if (!oldData.getDesc().equals(newData.getDesc())) {
                bundle.putString(DiffEntity.ENTITY_DESC, newData.getDesc());
            }
            if (!oldData.getState().equals(newData.getState())) {
                bundle.putString(DiffEntity.ENTITY_STATE, newData.getState());
            }
            if (!oldData.getTitle().equals(newData.getTitle())) {
                bundle.putString(DiffEntity.ENTITY_STATE, newData.getTitle());
            }

            return bundle;
        }
    }

}