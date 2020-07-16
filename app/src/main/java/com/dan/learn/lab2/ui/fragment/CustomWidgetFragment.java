package com.dan.learn.lab2.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.widget.RoundCircleDrawable;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomWidgetFragment extends BaseFragment {

    @BindView(R.id.vf_flipper)
    ViewFlipper vf_flipper;
    @BindView(R.id.iv_image)
    ImageView iv_image;


    private CustomWidgetFragment(String title) {
        super(title);
    }

    public static CustomWidgetFragment getInstance(String title) {
        return new CustomWidgetFragment(title);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_custom_widget;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
//        LayoutInflater inflater = getLayoutInflater();
//        View advertisement = inflater.inflate(R.layout.view_advertisement_0, null);


    }

    @OnClick(R.id.bt_action)
    public void onClick(View v) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        RoundCircleDrawable drawable = new RoundCircleDrawable(bitmap);
        drawable.setType(RoundCircleDrawable.Type.TYPE_CIRCLE);
        iv_image.setBackground(drawable);
    }

}
