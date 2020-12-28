package com.dan.learn.lab2.adapter.refresh;

import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import com.dan.learn.lab2.entity.TagBean;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public class UnderLineNavigatorAdapter  {
    private List<TagBean> tagList;
    private Context context;
    private ViewPager viewPager;
    public UnderLineNavigatorAdapter(Context context, List<TagBean> tagList){
        this.tagList = tagList;
        this.context = context;
    }
    public void setRelateViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
    }
//    @Override
//    public int getCount() {
//        return tagList == null ? 0 : tagList.size();
//    }
//
//    @Override
//    public IPagerTitleView getTitleView(Context context, final int index) {
//        // 缩放 + 颜色渐变
//        ScaleTransitionPagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
//        simplePagerTitleView.setText(tagList.get(index).getTagName());
//        simplePagerTitleView.setMinScale(0.83f);
//        simplePagerTitleView.setTextSize(18);
//        simplePagerTitleView.setNormalColor(context.getResources().getColor(R.color.color_selector));
//        simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.color_gold_text));
//        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(viewPager !=null && viewPager.getAdapter().getCount() > index){
//                    viewPager.setCurrentItem(index, true);
//                }
//
//            }
//        });
//        return simplePagerTitleView;
//    }
//
//    @Override
//    public IPagerIndicator getIndicator(Context context) {
//        LinePagerIndicator indicator = new LinePagerIndicator(context);
//        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//        indicator.setColors(Color.parseColor("#A5862D"));
//        return indicator;
//    }
}
