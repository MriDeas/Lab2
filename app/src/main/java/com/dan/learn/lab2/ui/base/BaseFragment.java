package com.dan.learn.lab2.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    protected abstract int getContentLayout();

    private Unbinder mBinder;
    private String mTitle;
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected BaseFragment(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayout(), container, false);
        mBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected void promptMsg(String prompt) {
        Toast.makeText(mContext, prompt, Toast.LENGTH_SHORT).show();
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
