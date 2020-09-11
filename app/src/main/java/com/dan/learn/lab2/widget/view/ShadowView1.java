package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class ShadowView1 extends androidx.appcompat.widget.AppCompatButton {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ShadowView1(Context context) {
        super(context);
    }

    public ShadowView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(30);
        mPaint.setStrokeWidth(2);

        getPaint().setShadowLayer(5, 10, 5, Color.GRAY);
        mPaint.setColor(Color.BLACK);
    }


}
