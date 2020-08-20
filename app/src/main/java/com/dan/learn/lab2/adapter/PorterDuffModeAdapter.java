package com.dan.learn.lab2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.PorterDuffEntity;

import java.util.List;

public class PorterDuffModeAdapter extends BaseAdapter {

    private Context context;
    private List<PorterDuffEntity> list;

    public PorterDuffModeAdapter(Context context, List<PorterDuffEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public PorterDuffEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.item_porter_duff_mode, null);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_name.setText(getItem(position).getModeName());
        return convertView;
    }

    private static class Holder {
        TextView tv_name;
    }
}
