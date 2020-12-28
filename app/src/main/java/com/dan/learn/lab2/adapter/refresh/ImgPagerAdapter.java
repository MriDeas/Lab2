package com.dan.learn.lab2.adapter.refresh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public class ImgPagerAdapter extends PagerAdapter {
    private Context context = null;
    private int[] list;
    public ImgPagerAdapter(Context context, int[] list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(list[position]);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams();
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
