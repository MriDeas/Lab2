package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintColorView extends View {

    private static final int FILTER_MODE_LIGHT = 1;
    private static final int FILTER_PORTER_DUFF = 2;
    private static final int FILTER_COLOR_MATRIX = 3;

    private Paint mPaint;

    private RectF rectF = new RectF();

    private int colorMul;
    private int colorAdd;
    private int filterMode = 1;
    private Bitmap bitmap;

    public PaintColorView(Context context) {
        this(context, null);
    }

    public PaintColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRoundRect(canvas);
        if (filterMode == FILTER_MODE_LIGHT) {
            drawLightColorFilterRect(canvas);
            drawLightColorFilterBitmap(canvas);
        }
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setARGB(100, 99, 100, 102);
        canvas.drawRect(10, 10, 200, 300, mPaint);
    }

    public void setLightColorFilter(ColorFilter filter) {
        filterMode = FILTER_MODE_LIGHT;
        if (mPaint != null) {
            mPaint.setColorFilter(filter);
        }
        invalidate();
    }

    public void setLightColorFilterValue(int mul, int add) {
        colorAdd = add;
        colorMul = mul;
        invalidate();
    }

    private void drawLightColorFilterRect(Canvas canvas) {
        rectF.set(230, 10, 360, 300);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.parseColor("#326b6b"));
        String text = "mul :" + colorMul + " add:" + colorAdd;
        canvas.drawText(text, 200, 310, mPaint);
    }

    private void drawLightColorFilterBitmap(Canvas canvas) {
        canvas.drawBitmap(bitmap, 380, 10, mPaint);
    }

}
