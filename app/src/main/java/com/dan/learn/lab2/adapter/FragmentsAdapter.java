package com.dan.learn.lab2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dan.learn.lab2.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/29
 * Description:
 * Modify time:
 */
public class FragmentsAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mList;

    public FragmentsAdapter(@NonNull FragmentManager fm, List<BaseFragment> list) {
        super(fm,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mList = list;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getTitle();
    }


    public BaseFragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
