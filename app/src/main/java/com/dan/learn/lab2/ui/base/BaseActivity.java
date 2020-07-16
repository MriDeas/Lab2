package com.dan.learn.lab2.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.dan.learn.lab2.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentLayout();

    protected Activity mContext;

    private Unbinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getContentLayout());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bac_solid_action_bar));
        }
        mBinder = ButterKnife.bind(this);
    }

    protected void promptMsg(String prompt) {
        Toast.makeText(mContext, prompt, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
        mBinder = null;
    }
}
