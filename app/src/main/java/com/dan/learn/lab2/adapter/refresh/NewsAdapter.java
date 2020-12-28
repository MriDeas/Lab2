package com.dan.learn.lab2.adapter.refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dan.learn.lab2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.VH> {
    private Context context;
    private List<String> mDatas;
    public NewsAdapter(Context context){
        this.context = context;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tvContent.setText(mDatas.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
    /** 更新数据，替换原有数据 */
    public void updateItems(List<String> items) {
        mDatas = items;
        notifyDataSetChanged();
    }

    /** 在列表尾添加一串数据 */
    public void addItems(List<String> items) {
        int start = mDatas.size();
        mDatas.addAll(items);
        // notifyItemRangeChanged(start, items.size());会闪屏
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.content)
        TextView tvContent;

        public VH(View viewItem){
            super(viewItem);
            ButterKnife.bind(this, itemView);
        }
    }
}
