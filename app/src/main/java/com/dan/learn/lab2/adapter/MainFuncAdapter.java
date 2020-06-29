package com.dan.learn.lab2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.entity.FuncEntity;
import com.dan.learn.lab2.entity.FuncGroupEntity;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/10
 * Description:
 * Modify time:
 */
public class MainFuncAdapter extends BaseExpandableListAdapter {

    private List<FuncGroupEntity> mData;

    private LayoutInflater mInflater;

    public MainFuncAdapter(List<FuncGroupEntity> data, Context context) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        FuncGroupEntity entity = mData.get(groupPosition);
        return entity != null && entity.getChildren() != null ? entity.getChildren().size() : 0;
    }

    @Override
    public FuncGroupEntity getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public FuncEntity getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder mGroupHolder;
        if (convertView == null) {
            mGroupHolder = new GroupHolder();
            convertView = mInflater.inflate(R.layout.item_group_title, null, false);
            mGroupHolder.tv_text = convertView.findViewById(R.id.tv_group_title);
            convertView.setTag(mGroupHolder);
        } else {
            mGroupHolder = (GroupHolder) convertView.getTag();
        }

        mGroupHolder.tv_text.setText(getGroup(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder mChildHolder;
        if (convertView == null) {
            mChildHolder = new ChildHolder();
            convertView = mInflater.inflate(R.layout.item_func_child_layout, null);
            convertView.setTag(mChildHolder);
            mChildHolder.tv_name = convertView.findViewById(R.id.tv_name);
            mChildHolder.tv_desc = convertView.findViewById(R.id.tv_desc);
        } else {
            mChildHolder = (ChildHolder) convertView.getTag();
        }

        FuncEntity child = getChild(groupPosition, childPosition);
        mChildHolder.tv_name.setText(child.getName());
        mChildHolder.tv_desc.setText(child.getDesc());
        mChildHolder.tv_desc.setVisibility(TextUtils.isEmpty(child.getDesc()) ? View.GONE : View.VISIBLE);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupHolder {
        private TextView tv_text;
    }

    private static class ChildHolder {
        private TextView tv_name;
        private TextView tv_desc;
    }
}
