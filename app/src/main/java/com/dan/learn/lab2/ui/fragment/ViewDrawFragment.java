package com.dan.learn.lab2.ui.fragment;

import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.adapter.PorterDuffModeAdapter;
import com.dan.learn.lab2.entity.PorterDuffEntity;
import com.dan.learn.lab2.ui.base.BaseFragment;
import com.dan.learn.lab2.widget.view.PaintColorView;
import com.dan.learn.lab2.widget.view.PaintMultiEffectView;

import java.util.ArrayList;
import java.util.List;

/**
 * 绘制
 */
public class ViewDrawFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {

    private PorterDuff.Mode[] X_FER_MODES = new PorterDuff.Mode[]{
            PorterDuff.Mode.ADD, PorterDuff.Mode.DARKEN, PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.DST_IN, PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.DST_OVER, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.OVERLAY, PorterDuff.Mode.SCREEN, PorterDuff.Mode.SRC,
            PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.SRC_IN, PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.XOR};

    private String[] X_FER_MODE_STR_ARR = new String[]{
            "ADD", "DARKEN", "DST", "DST_ATOP",
            "DST_IN", "DST_OUT", "DST_OVER",
            "LIGHTEN", "MULTIPLY", "OVERLAY",
            "SCREEN", "SRC", "SRC_ATOP",
            "SRC_IN", "SRC_OVER", "XOR"};


    private PaintColorView pcv_color_view;
    private PaintColorView pcv_color_view_2;
    private Switch switch_light_button;
    private Switch switch_porter_duff_button;
    private Button bt_change_mode;
    private SeekBar seek_red;
    private SeekBar seek_green;
    private SeekBar seek_blue;
    private SeekBar seek_alpha;
    private PaintColorView pcv_color_view_3;

    private int colorRed;
    private int colorGreen;
    private int colorBlue;
    private int colorAlpha;
    private int offset = 100;
    private float[] matrixColorArray;
    private GridView gv_porter_duff_modes;

    private List<PorterDuffEntity> porterDuffEntities = new ArrayList<>();
    private PaintMultiEffectView pmev_4;

    public static ViewDrawFragment getInstance(String title, @LayoutRes int layoutId) {
        return new ViewDrawFragment(title, layoutId);
    }

    private ViewDrawFragment(String title, @LayoutRes int layoutId) {
        super(title);
        mLayoutRes = layoutId;
    }

    private int mLayoutRes;
    private int mCurrentPorterColorMode;

    @Override
    protected int getContentLayout() {
        return mLayoutRes;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch_light_button = view.findViewById(R.id.switch_light_button);
        pcv_color_view = view.findViewById(R.id.pcv_color_view);
        pcv_color_view_2 = view.findViewById(R.id.pcv_color_view_2);
        switch_porter_duff_button = view.findViewById(R.id.switch_porter_duff_button);
        bt_change_mode = view.findViewById(R.id.bt_change_mode);

        gv_porter_duff_modes = view.findViewById(R.id.gv_porter_duff_modes);
        pmev_4 = view.findViewById(R.id.pmev_4);

        seek_red = view.findViewById(R.id.seek_red);
        seek_green = view.findViewById(R.id.seek_green);
        seek_blue = view.findViewById(R.id.seek_blue);
        seek_alpha = view.findViewById(R.id.seek_alpha);
        pcv_color_view_3 = view.findViewById(R.id.pcv_color_view_3);

        initPorterDuffData();
        initSeekBar();
        initPorterDuffColorFilter();
        initMatrixColorFilter();
        initPorterDuffMode();
    }

    private void initPorterDuffData() {
        porterDuffEntities.clear();
        for (int i = 0; i < X_FER_MODES.length; i++) {
            PorterDuffEntity duffEntity = new PorterDuffEntity(X_FER_MODES[i], X_FER_MODE_STR_ARR[i]);
            porterDuffEntities.add(duffEntity);
        }
    }

    private void initSeekBar() {
        if (switch_light_button != null && pcv_color_view != null) {
            switch_light_button.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    //LightingColorFilter 0xffffff = 0xRRGGBB 分别是 R = ff, G = ff, B = ff 不支持透明度
                    //这里 mul = 0x0000ff  add = 0x0000ff
                    //mPaint.setColor(Color.rgb(0,255, 0)); 颜色只有绿色
                    //最终结果是 ： R = 0 * (mul)0x00 + (add)0x00 = 0
                    //            G = 255 * (mul)0x00 + (add)0x00 = 0
                    //            B = 0 * (mul)0x00 + (add)0xff = 255
                    //设置lightColorFilter 之后颜色就成了 蓝色

                    LightingColorFilter colorFilter = new LightingColorFilter(0x0000FF, 0x0000FF);
                    pcv_color_view.setLightColorFilter(colorFilter);
                } else {
                    pcv_color_view.setLightColorFilter(null);
                }

            });
        }
    }

    private void initPorterDuffColorFilter() {
        if (switch_porter_duff_button != null) {
            switch_porter_duff_button.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    int color = getResources().getColor(R.color.design_default_color_primary);
                    PorterDuffColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.CLEAR);
                    pcv_color_view_2.setPorterDuffColorFilter(filter, 0);
                } else {
                    pcv_color_view_2.setPorterDuffColorFilter(null, 0);
                }
            });

            bt_change_mode.setOnClickListener(v -> {
                PorterDuff.Mode[] porterColorModes = pcv_color_view_2.getPorterColorModes();
                if (mCurrentPorterColorMode >= porterColorModes.length) {
                    mCurrentPorterColorMode = 0;
                }
                PorterDuff.Mode mode = porterColorModes[mCurrentPorterColorMode];
                PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, mode);
                pcv_color_view_2.setPorterDuffColorFilter(filter, mCurrentPorterColorMode);
                mCurrentPorterColorMode++;
            });
        }
    }

    private void initMatrixColorFilter() {
        // 4 row * 5 column
        matrixColorArray = new float[20];
        if (seek_red != null && pcv_color_view_3 != null) {
            seek_red.setOnSeekBarChangeListener(this);
            seek_green.setOnSeekBarChangeListener(this);
            seek_blue.setOnSeekBarChangeListener(this);
            seek_alpha.setOnSeekBarChangeListener(this);
        }
    }

    private void initPorterDuffMode() {
        if (gv_porter_duff_modes != null && pmev_4 != null) {
            PorterDuffModeAdapter adapter = new PorterDuffModeAdapter(mContext, porterDuffEntities);
            gv_porter_duff_modes.setAdapter(adapter);
            gv_porter_duff_modes.setOnItemClickListener((parent, view, position, id) -> {
                PorterDuffEntity item = adapter.getItem(position);
                PorterDuffXfermode xFermode = new PorterDuffXfermode(item.getMode());
                pmev_4.setXFerPorterDuffMode(xFermode);
            });
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int id = seekBar.getId();
        int progress = seekBar.getProgress();
        switch (id) {
            case R.id.seek_red:
                colorRed = progress;
                matrixColorArray[0] = colorRed;
                matrixColorArray[1] = 0;
                matrixColorArray[2] = 0;
                matrixColorArray[3] = 0;
                matrixColorArray[4] = offset;
                break;
            case R.id.seek_green:
                colorGreen = progress;
                matrixColorArray[5] = colorGreen;
                matrixColorArray[6] = 0;
                matrixColorArray[7] = 0;
                matrixColorArray[8] = 0;
                matrixColorArray[9] = offset;
                break;
            case R.id.seek_blue:
                colorBlue = progress;
                matrixColorArray[10] = colorBlue;
                matrixColorArray[11] = 0;
                matrixColorArray[12] = 0;
                matrixColorArray[13] = 0;
                matrixColorArray[14] = offset;
                break;
            case R.id.seek_alpha:
                colorAlpha = progress;
                matrixColorArray[15] = 100;
                matrixColorArray[16] = 100;
                matrixColorArray[17] = 100;
                matrixColorArray[18] = 100;
                matrixColorArray[19] = 100;
                break;
        }

        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(matrixColorArray);
        pcv_color_view_3.setMatrixColorFilter(colorMatrixColorFilter);
    }
}
