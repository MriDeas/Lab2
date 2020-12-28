package com.dan.learn.lab2.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;

import butterknife.BindView;

public class RecyclerActivity extends BaseActivity {

    @BindView(R.id.rv_recycler_view)
    RecyclerView rv_recycler_view;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private static class Adapter extends RecyclerView.Adapter<CommonHolder> {

        @NonNull
        @Override
        public CommonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull CommonHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private static class CommonHolder extends RecyclerView.ViewHolder {

        public CommonHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class HeaderHolder extends RecyclerView.ViewHolder{
        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}