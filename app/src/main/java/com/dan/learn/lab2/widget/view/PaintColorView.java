package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintColorView extends View {

    private Paint mPaint;
    private ColorMatrixColorFilter matrixFilter;
    private LightingColorFilter lightingColorFilter;

    public PaintColorView(Context context) {
        this(context,null);
    }

    public PaintColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        matrixFilter = new ColorMatrixColorFilter(new float[]{0.5f, 0.35f});
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRoundRect(canvas);
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(100);
        mPaint.setARGB(100, 99, 100, 102);
        canvas.drawRect(100, 100, 200, 300, mPaint);
    }

    private void drawLinearColorFilter(Canvas canvas) {
        mPaint.setColorFilter(matrixFilter);
    }

    private void drawColorFilterMatrix(Canvas canvas) {
        setLightingFilter();
        mPaint.setColor(Color.rgb(30, 60, 120));
    }

    private void setLightingFilter() {
        if (lightingColorFilter == null) {
            lightingColorFilter = new LightingColorFilter(0xFFFF0FFF, 0x000000FF);
            mPaint.setColorFilter(lightingColorFilter);
        }
    }
}
