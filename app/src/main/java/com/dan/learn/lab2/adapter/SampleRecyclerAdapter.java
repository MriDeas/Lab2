package com.dan.learn.lab2.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.DiffEntity;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/7/7
 * Description:
 * Modify time:
 */
public class SampleRecyclerAdapter extends Adapter<SampleRecyclerAdapter.Holder> {

    private Context mContext;
    private List<DiffEntity> list;

    public SampleRecyclerAdapter(Context context, List<DiffEntity> list) {
        this.mContext = context;
        this.list = list;
    }

    public void setData(List<DiffEntity> newData) {
//        list.clear();
//        list.addAll(newData);
        list = newData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_diff_recycler, null);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_desc1.setText(list.get(position).getDesc());
        holder.tv_state.setText(list.get(position).getState());
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (!payloads.isEmpty()) {
            Bundle bundle = (Bundle) payloads.get(0);
            String desc = bundle.getString(DiffEntity.ENTITY_DESC);
            String state = bundle.getString(DiffEntity.ENTITY_STATE);
            String title = bundle.getString(DiffEntity.ENTITY_TITLE);
            for (String key : bundle.keySet()) {
                switch (key) {
                    case DiffEntity.ENTITY_DESC:
                        holder.tv_desc1.setText(desc);
                        break;
                    case DiffEntity.ENTITY_STATE:
                        holder.tv_state.setText(state);
                        break;
                    case DiffEntity.ENTITY_TITLE:
                        holder.tv_title.setText(title);
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected static class Holder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_desc1;
        private TextView tv_state;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc1 = itemView.findViewById(R.id.tv_desc);
            tv_state = itemView.findViewById(R.id.tv_state);
        }
    }
}
