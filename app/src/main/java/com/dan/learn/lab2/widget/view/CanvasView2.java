package com.dan.learn.lab2.widget.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView2 extends View {

    private RectF rect = new RectF();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CanvasView2(Context context) {
        super(context);
    }

    public CanvasView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setColor(Color.MAGENTA);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

}
