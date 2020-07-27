package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Locale;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintTextView extends View {

    private Paint paint;

    public PaintTextView(Context context) {
        this(context, null);
    }

    public PaintTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnderLine(canvas);
        drawTypeFace(canvas);
        drawBoldAndSkew(canvas);
        drawTextWithLetterSpacing(canvas);
        drawElegantTextHeight(canvas);
        drawDeleteAndScaleText(canvas);
    }

    private void drawText(Canvas canvas) {


        paint.setTextAlign(Paint.Align.LEFT); // right 文字完全绘制在原点的左边 left 绘制在原点的右边
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            paint.setTextLocale(Locale.CHINESE);
        }
//        paint.setTextScaleX(0.97f); //文字横向的缩放
        paint.setTextSkewX(0); //设置 文字偏斜  正值偏向左，负值 偏向右
//        paint.setStrikeThruText(true); //辅助 使用或清理 STRIKE_THRU_TEXT_FLAG 标记
        paint.setFakeBoldText(true); //加粗
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setElegantTextHeight(true);
        }
        paint.setUnderlineText(true); //设置下划线
        paint.setSubpixelText(true); //辅助或清理 SUBPIXEL_TEXT_FLAG 亚像素 标记
        paint.setLinearText(true); //辅助设置或清理

        paint.setTypeface(Typeface.SERIF); //字体

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setLetterSpacing(0.5f); // 处理字母或文字间隔
        }
    }

    private Rect paintTextRect = new Rect();
    private int lastHeight = 0;
    private int heightSum = 30;

    private void drawUnderLine(Canvas canvas) {
        reset();
        String text = "paint.setUnderlineText(true)";
        paint.setUnderlineText(true);
        paint.getTextBounds(text, 0, text.length(), paintTextRect);
        lastHeight = paintTextRect.height();
        canvas.drawText(text, 10, heightSum, paint);
        heightSum = lastHeight + 60;
    }

    private void drawTypeFace(Canvas canvas) {
        reset();
        String text = "paint.setTypeface(Typeface.SERIF)";
        paint.setTypeface(Typeface.SERIF); //字体
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawBoldAndSkew(Canvas canvas) {
        reset();
        paint.setTextSkewX(0.5f); //设置 文字偏斜  正值偏向左，负值 偏向右
        paint.setFakeBoldText(true); //加粗

        String text = "setTextSkewX & setFakeBoldText";
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawTextWithLetterSpacing(Canvas canvas) {
        reset();
        String text = "setLetterSpacing(0.05f)";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setLetterSpacing(0.05f);
        }
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }


    private void drawElegantTextHeight(Canvas canvas) {
        reset();
        String text = "setElegantTextHeight(true) ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setElegantTextHeight(true);
        }

        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawDeleteAndScaleText(Canvas canvas) {
        reset();
        String text = "删除线setStrikeThruText & setScaleX";
        paint.setStrikeThruText(true);
        paint.setTextScaleX(1.25f);
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void measureTextHeight(String text) {
        paint.getTextBounds(text, 0, text.length(), paintTextRect);
        lastHeight = paintTextRect.height();
        heightSum += lastHeight + 30;
    }

    private void reset() {
        paint.setTextSkewX(0);
        paint.setUnderlineText(false);
        paint.setFakeBoldText(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setLetterSpacing(0);
        }
    }

}
