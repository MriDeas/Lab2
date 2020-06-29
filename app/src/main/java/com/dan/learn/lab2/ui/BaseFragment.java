package com.dan.learn.lab2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dan.learn.lab2.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private Unbinder mBinder;

    protected abstract int getContentLayout();

    private String mTitle;

    public BaseFragment(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayout(), container, false);
        mBinder = ButterKnife.bind(this,rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null) {
            mBinder.unbind();
        }
        mBinder = null;
    }
}
