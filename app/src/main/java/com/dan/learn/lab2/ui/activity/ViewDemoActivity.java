package com.dan.learn.lab2.ui.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.ui.base.BaseMultiFragmentActivity;
import com.dan.learn.lab2.ui.fragment.ViewDemoFragment;

import java.util.List;

public class ViewDemoActivity extends BaseMultiFragmentActivity {

    @Override
    protected List<BaseFragment> getFragments(List<BaseFragment> fragments) {
//        fragments.add(new ViewDemoFragment("Tab_Layout", R.layout.fragment_tab_layout));
//        fragments.add(new ViewDemoFragment("Touch_View", R.layout.fragment_touch_layout));
        fragments.add(new ViewDemoFragment("View_Pager", R.layout.fragment_view_pager));

        return fragments;
    }

//    @Override
//    protected int getContentLayout() {
//        return R.layout.fragment_view_pager;
//    }

    private void animation() {
        Animation a = new ScaleAnimation(1, 1, 1.5f, 1.5f);
        Animation b = new TranslateAnimation(0, 0, 100, 100);
        Animation c = new AlphaAnimation(1, 0.86f);
        Animation d = new RotateAnimation(0, 90);
        ObjectAnimator f = ObjectAnimator.ofFloat(null, "title", 1, 2, 3, 4, 5, 6, 7);
        ValueAnimator g = ValueAnimator.ofFloat(100);
        PropertyValuesHolder p = PropertyValuesHolder.ofFloat("translationX",300f);
    }
}
