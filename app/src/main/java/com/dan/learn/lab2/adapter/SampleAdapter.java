package com.dan.learn.lab2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by: dan
 * Created time: 2020/7/6
 * Description:
 * Modify time:
 */
public class SampleAdapter extends BaseAdapter {

    private List<Map<String, String>> mList;
    private List<String> mList2;
    private List<Item> mList3;
    private Context mContext;

    public SampleAdapter(Context context, List<Map<String, String>> data, List<String> list2, List<Item> list3) {
        mList = data;
        mList2 = list2;
        mList3 = list3;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList3 == null ? 0 : mList3.size();
    }

    @Override
    public Item getItem(int position) {
        return mList3.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_sample_list_layout, null);
            holder = new Holder();
            holder.tv_item = convertView.findViewById(R.id.tv_item);
            holder.tv_item_append = convertView.findViewById(R.id.tv_item_append);
            holder.cb_box = convertView.findViewById(R.id.cb_box);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.cb_box.setOnClickListener(null);
        holder.cb_box.setOnClickListener(v -> {
            getItem(position).setChecked(holder.cb_box.isChecked());
        });
        holder.cb_box.setChecked(getItem(position).isChecked());
        holder.tv_item.setText("item " + position);
        holder.tv_item_append.setText(String.valueOf(position));
        return convertView;
    }

    private static class Holder {
        private TextView tv_item;
        private TextView tv_item_append;
        private Switch cb_box;
    }
}
