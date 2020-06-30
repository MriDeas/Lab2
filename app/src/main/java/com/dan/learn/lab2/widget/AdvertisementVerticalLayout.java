package com.dan.learn.lab2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/6/30
 * Description:
 * Modify time:
 */
public class AdvertisementVerticalLayout extends FrameLayout {

    private Animation animationIn;
    private Animation animationOut;
    private long interval;
    private boolean autoPlay;

    public AdvertisementVerticalLayout(@NonNull Context context) {
        this(context, null);
    }

    public AdvertisementVerticalLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AdvertisementVerticalLayout);
        int animIn = ta.getResourceId(R.styleable.AdvertisementVerticalLayout_animIn, R.anim.anim_marquee_in);
        int animOut = ta.getResourceId(R.styleable.AdvertisementVerticalLayout_animIn, R.anim.anim_marquee_out);
        interval = ta.getInteger(R.styleable.AdvertisementVerticalLayout_interval, 2000);
        autoPlay = ta.getBoolean(R.styleable.AdvertisementVerticalLayout_autoStart, false);
        ta.recycle();

        animationIn = AnimationUtils.loadAnimation(context, animIn);
        animationIn.setFillAfter(true);
        animationOut = AnimationUtils.loadAnimation(context, animOut);
        animationOut.setFillAfter(true);
        animationIn.setInterpolator(new AccelerateDecelerateInterpolator());
        animationOut.setInterpolator(new AccelerateDecelerateInterpolator());
        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animOutDelay();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animIn();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (autoPlay) {
            start();
        }
    }

    public void start() {
        if (animationIn == null || animationOut == null) {
            return;
        }
        animIn();
    }

    private void animOutDelay() {
        postDelayed(this::animOut, interval);
    }

    private void animIn() {
        View firstChild = getChildAt(0);
        if (firstChild != null) {
            firstChild.startAnimation(animationIn);
        }
    }

    private void animOut() {
        View firstChild = getChildAt(0);
        if (firstChild != null) {
            firstChild.startAnimation(animationOut);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View child = getChildAt(0);
        if (child != null) {
            child.clearAnimation();
        }
    }
}
