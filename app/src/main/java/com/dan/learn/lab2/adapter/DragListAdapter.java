package com.dan.learn.lab2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dan.learn.lab2.R;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/8
 * Description:
 * Modify time:
 */
public class DragListAdapter extends BaseAdapter {

    private List<String> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public DragListAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.item_drag_list, parent, false);
            holder.tv_text = convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_text.setText(getItem(position));

        return convertView;
    }

    private static class Holder {
        TextView tv_text;
    }
}

