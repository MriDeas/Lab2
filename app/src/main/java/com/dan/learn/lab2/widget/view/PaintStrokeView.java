package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintStrokeView extends View {

    private Paint paint;
    private int centerX;
    private int centerY;
    private Path linePath1;
    private Path linePath2;
    private Path linePath3;
    private Path linePath4;
    private int width;
    private int height;

    public PaintStrokeView(Context context) {
        this(context, null);
    }

    public PaintStrokeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint();
        paint.setAntiAlias(true);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStrokeLine(canvas);
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
