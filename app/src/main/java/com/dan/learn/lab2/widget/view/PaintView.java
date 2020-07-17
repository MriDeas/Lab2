package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dan.learn.lab2.utils.SizeUtil;

import java.util.Locale;

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

//        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        setPaintFlag();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setText();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setText() {
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.LEFT); // right 文字完全绘制在原点的左边 left 绘制在原点的右边
        paint.setTextLocale(Locale.CHINESE);
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


    public void setPaintFlag(){
        // STRIKE_THRU_TEXT_FLAG 相当于加了删除线，文字中间部位加的线
        // LINEAR_TEXT_FLAG
        // SUBPIXEL_TEXT_FLAG
        paint.setFlags(Paint.LINEAR_TEXT_FLAG);
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
        paint.setAlpha(color);
        paint.setARGB(color, color, color, color);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(new float[]{0.5f, 0.35f});
        paint.setColorFilter(filter);
    }

    public void setPaintStroke() {
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setStrokeMiter(1);
    }

    public void setPaintOther(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setBlendMode(BlendMode.DIFFERENCE);
        }

        paint.setDither(true);
        paint.setHinting();
        paint.setXfermode();
        paint.setShader();
        paint.setFontVariationSettings();
        paint.setFontFeatureSettings();
        paint.setShadowLayer();
        paint.setMaskFilter();
        paint.setPathEffect();
        paint.setStartHyphenEdit();

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
        String text = "绘制 dispatchDraw 函数 super 前";
        String text2 = "绘制 dispatchDraw 函数 super 后";

        int textW = (int) paint.measureText(text);
        int textW2 = (int) paint.measureText(text2);

        paint.setColor(Color.argb(255, 47, 79, 79));

        canvas.drawText(text, centerX - (textW >> 1), centerY - 200, paint);
        super.dispatchDraw(canvas);
        canvas.drawText(text2, centerX - (textW2 >> 1), centerY + 200, paint);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

        String text = "Foreground 绘制";
        int textW = (int) paint.measureText(text);
        paint.setColor(Color.argb(120, 255, 69, 0));
        canvas.drawARGB(60, 10, 191, 255);
        canvas.drawText(text, centerX - (textW >> 1), centerY, paint);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY - 200, 100, paint);
    }

}
