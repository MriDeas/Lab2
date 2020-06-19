package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.BaseActivity;
import com.dan.learn.lab2.widget.DragUpDownLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖拽控件
 */
public class DragViewActivity extends BaseActivity {

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);

        ListView lv_list = findViewById(R.id.lv_list);
        inflater = LayoutInflater.from(this);
        bindingData(lv_list);
    }

    private void bindingData(ListView lv) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("hello " + i);
        }

        ListViewAdapter adapter = new ListViewAdapter(data);
        lv.setAdapter(adapter);
    }

    private class ListViewAdapter extends BaseAdapter {

        private List<String> mData;

        private ListViewAdapter(List<String> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_group_title, parent, false);
            }
            TextView tv_group_title = convertView.findViewById(R.id.tv_group_title);
            tv_group_title.setText(getItem(position));
            return convertView;
        }
    }


}
