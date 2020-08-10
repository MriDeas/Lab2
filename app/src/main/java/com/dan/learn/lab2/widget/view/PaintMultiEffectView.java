package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by: dan
 * Created time: 2020/8/10
 * Description:
 * Modify time:
 */
public class PaintMultiEffectView extends View {

    private Paint mPaint;
    private MaskFilter innerMaskFilter;
    private BlurMaskFilter normalMaskFilter;
    private BlurMaskFilter outerMaskFilter;
    private BlurMaskFilter solidMaskFilter;

    public PaintMultiEffectView(Context context) {
        super(context);
    }

    public PaintMultiEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.BLUE);

        innerMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
        normalMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
        outerMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER);
        solidMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);

//        mPaint.setShadowLayer();
//        mPaint.setShader();
//        mPaint.setXfermode();
//        mPaint.setBlendMode();
//        mPaint.setMaskFilter();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawMaskFilter1(canvas);
    }

    private void drawMaskFilter1(Canvas canvas) {
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        canvas.drawColor(Color.DKGRAY);
        mPaint.setMaskFilter(innerMaskFilter);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(50, 50, 150, 150, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setMaskFilter(normalMaskFilter);
        canvas.drawRect(200, 50, 300, 150, mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setMaskFilter(outerMaskFilter);
        canvas.drawRect(350, 50, 450, 150, mPaint);

        mPaint.setColor(Color.CYAN);
        mPaint.setMaskFilter(solidMaskFilter);
        canvas.drawRect(500, 50, 600, 150, mPaint);

    }
}
