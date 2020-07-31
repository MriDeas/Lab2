package com.dan.learn.lab2.ui.fragment;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.widget.view.PaintColorView;

/**
 * 绘制
 */
public class ViewDrawFragment extends BaseFragment {

    private PaintColorView pcv_color_view;
    private Switch switch_light_button;

    public static ViewDrawFragment getInstance(String title, @LayoutRes int layoutId) {
        return new ViewDrawFragment(title, layoutId);
    }

    private ViewDrawFragment(String title, @LayoutRes int layoutId) {
        super(title);
        mLayoutRes = layoutId;
    }

    private int mLayoutRes;

    @Override
    protected int getContentLayout() {
        return mLayoutRes;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch_light_button = view.findViewById(R.id.switch_light_button);
        pcv_color_view = view.findViewById(R.id.pcv_color_view);
        initSeekBar();
    }

    private void initSeekBar() {
        if (switch_light_button != null && pcv_color_view != null) {
            switch_light_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        LightingColorFilter colorFilter = new LightingColorFilter(0xFFFFFF, 0x0000FF);
                        pcv_color_view.setLightColorFilter(colorFilter);
                    } else {
                        pcv_color_view.setLightColorFilter(null);
                    }

                }
            });
        }
    }
}
