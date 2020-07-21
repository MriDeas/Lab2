package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
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
    private Path linePath1;
    private Path linePath2;
    private Path linePath3;
    private Path linePath4;
    private LightingColorFilter lightingColorFilter;

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


    public void setPaintFlag() {
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
        String text = "绘制 dispatchDraw 函数 super 前";
        String text2 = "绘制 dispatchDraw 函数 super 后";
        paint.setColorFilter(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
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
        paint.setColorFilter(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        String text = "Foreground 绘制";
        int textW = (int) paint.measureText(text);
        paint.setColor(Color.argb(100, 255, 69, 0));
        canvas.drawARGB(60, 10, 191, 255);
        canvas.drawText(text, centerX - (textW >> 1), centerY, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColorFilter(null);
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY - 200, 100, paint);
        drawStrokeLine(canvas);

        drawColorFilterMatrix(canvas);
    }

    private void drawColorFilterMatrix(Canvas canvas) {
        setLightingFilter();
        paint.setColor(Color.rgb(30,60,120));
        canvas.drawRect(centerX >> 3, 500, centerX - 200, 600, paint);
    }

    private void setLightingFilter() {
        if (lightingColorFilter == null) {
            lightingColorFilter = new LightingColorFilter(0xFFFF0FFF, 0x000000FF);
            paint.setColorFilter(lightingColorFilter);
        }
    }

    private void drawStrokeLine(Canvas canvas) {
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(centerX >> 2, 100, centerX - 100, 100, paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(centerX >> 2, 150, centerX - 100, 150, paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(centerX >> 2, 200, centerX - 100, 200, paint);

        paint.setTextSize(25);
        String text1 = "Butt 即定义的尺寸";
        String text2 = "ROUND 添加Round头";
        String text3 = "SQUARE 添加方形头";
        canvas.drawText(text1, centerX - 80, 100, paint);
        canvas.drawText(text2, centerX - 80, 150, paint);
        canvas.drawText(text3, centerX - 80, 200, paint);

        drawJoinStrokeLine(canvas);
    }

    private void drawJoinStrokeLine(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.GRAY);
        initLinePath(0);
        canvas.drawPath(linePath4, paint);
        initLinePath(1);
        canvas.drawPath(linePath1, paint);
        initLinePath(2);
        canvas.drawPath(linePath2, paint);
        initLinePath(3);
        canvas.drawPath(linePath3, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(18);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);

        canvas.drawText("Join null", centerX + 100, 390, paint);
        canvas.drawText("Join Round 圆角", centerX >> 2, 260, paint);
        canvas.drawText("Join BEVEL 直线", centerX + 100, 260, paint);
        canvas.drawText("Join MITER 锐角", (centerX >> 2) + 100, 380, paint);
    }

    private void initLinePath(int pathIndex) {
        if (linePath1 == null) {
            linePath1 = new Path();
            linePath2 = new Path();
            linePath3 = new Path();
            linePath4 = new Path();
        }

        if (pathIndex == 0) {
            paint.setStrokeMiter(1.5f);
            paint.setStrokeJoin(Paint.Join.MITER);
            linePath4.moveTo(centerX, 350);
            linePath4.lineTo(centerX + 300, 350);
            linePath4.lineTo(centerX + 300, 500);
            linePath4.close();
        }

        if (pathIndex == 1) {
            paint.setStrokeJoin(Paint.Join.ROUND);
            linePath1.moveTo(centerX >> 2, 240);
            linePath1.lineTo(centerX - 100, 240);
            linePath1.lineTo(centerX - 100, 320);
            linePath1.close();
        }

        if (pathIndex == 2) {
            paint.setStrokeJoin(Paint.Join.BEVEL);
            linePath2.moveTo(centerX, 240);
            linePath2.lineTo(centerX + 300, 240);
            linePath2.lineTo(centerX + 300, 330);
            linePath2.close();
        }


        if (pathIndex == 3) {
            paint.setStrokeMiter(60); //配合 paint.setStrokeJoin()使用，当连接角度为比较尖锐的锐角时，控制斜线的连接行为
            paint.setStrokeJoin(Paint.Join.MITER);
            linePath3.moveTo(centerX >> 2, 350);
            linePath3.lineTo(centerX - 100, 350);
            linePath3.lineTo(centerX - 100, 500);
//            linePath3.lineTo(centerX >> 2,350);
            linePath3.close();
        }

    }
}
