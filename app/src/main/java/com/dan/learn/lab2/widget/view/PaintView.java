package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.utils.SizeUtil;

/**
 * Created by: dan
 * Created time: 2020/7/16
 * Description:
 * Modify time:
 */
public class PaintView extends View {

    private Paint paint;
    private int width;
    private int height;
    private int centerX;
    private int centerY;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint();
        paint.setStrokeWidth(SizeUtil.dp2px(5));
        paint.setTextSize(30);
        paint.setAntiAlias(true);

    }


    public void setPaintOther() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setBlendMode(BlendMode.DIFFERENCE);
        }

        paint.setDither(true);
//        paint.setHinting();
//        paint.setXfermode();
//        paint.setShader();
//        paint.setFontVariationSettings();
//        paint.setFontFeatureSettings();
//        paint.setShadowLayer();
//        paint.setMaskFilter();
//        paint.setPathEffect();
//        paint.setStartHyphenEdit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setWordSpacing(10);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        centerX = width >> 1;
        centerY = height >> 1;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        String text = "绘制 dispatchDraw 函数 super 前 left";
        String text2 = "绘制 dispatchDraw 函数 super 后";


        paint.setTextSize(20);
        int textW = (int) paint.measureText(text);
        int textW2 = (int) paint.measureText(text2);

        paint.setColor(Color.BLUE);

        canvas.drawText(text, centerX - (textW >> 1), centerY - 200, paint);
        canvas.drawCircle(centerX - 230, centerY - 100, 100, paint);
        super.dispatchDraw(canvas);
        canvas.drawCircle(centerX + 230, centerY + 100, 100, paint);
        canvas.drawText(text2, centerX - (textW2 >> 1), centerY + 200, paint);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(centerX + 230, centerY - 200, 100, paint);
        String text1 = "super.onDrawForeground 绘制前";
        int textW = (int) paint.measureText(text1);
        canvas.drawText(text1, centerX - (textW >> 2), centerY - 300, paint);

        super.onDrawForeground(canvas);

        paint.setStyle(Paint.Style.FILL);
        String text = "super.onDrawForeground 绘制后";
        paint.setColor(Color.argb(150, 255, 69, 0));

        canvas.drawRect(centerX - 300, centerY - 300, centerX + 300, centerY + 300, paint);

        paint.setColor(Color.GRAY);
        canvas.drawText(text, centerX - (textW >> 1), centerY + 150, paint);
    }

    private String onDrawStr = "onDraw 绘制";

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setAlpha(255);
        paint.setStrokeWidth(50);
        canvas.drawLine(centerX, 0, centerX, 1200, paint);
        super.onDraw(canvas);

        int textWidth = (int) paint.measureText(onDrawStr);

        paint.setColor(Color.RED);
        paint.setAlpha(255);
        canvas.drawRect(centerX - 200, centerY - 100, centerX + 200, centerY + 100, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText(onDrawStr, centerX - (textWidth >> 1), centerY, paint);
    }


}
