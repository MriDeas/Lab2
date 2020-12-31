package com.dan.learn.lab2.ui.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
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

    private Animation animationT;
    private Animation animationS;
    private Animation animationA;
    private Animation animationR;
    private ObjectAnimator animationO;
    private ValueAnimator animationV;

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
        animationS = new ScaleAnimation(1, 1, 1.5f, 1.5f);
        animationT = new TranslateAnimation(0, 0, 100, 100);
        animationA = new AlphaAnimation(1, 0.86f);
        animationR = new RotateAnimation(0, 90);
        animationO = ObjectAnimator.ofFloat(null, "title", 1, 2, 3, 4, 5, 6, 7);
        animationV = ValueAnimator.ofFloat(100);
        PropertyValuesHolder p = PropertyValuesHolder.ofFloat("translationX",300f);

        animationS.setDuration(500);
        animationS.setFillAfter(true);
        animationS.setFillEnabled(true);
        animationS.setInterpolator(new AccelerateDecelerateInterpolator());
        animationS.setBackgroundColor(getResources().getColor(R.color.bg_light_color));
        animationS.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationS.start();
        animationS.startNow();
        new View(mContext).startAnimation(animationS);

        Matrix matrix = new Matrix();
        matrix.setScale(1,1);
        matrix.preScale(1,1);
        matrix.postScale(1,1);
        matrix.mapPoints(new float[5]);
        matrix.setTranslate(10,10);
        matrix.setRotate(0);
    }

    
}
