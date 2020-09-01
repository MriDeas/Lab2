package com.dan.learn.lab2.widget.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PathWavyLineView extends View {

    private Path path = new Path();
    private Path path2 = new Path();
    private Path path3 = new Path();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Point point1 = new Point(100, 300);
    private Point point2 = new Point(300, 300);
    private Point point3 = new Point(500, 300);
    private Point c_point1 = new Point(200, 200);
    private Point c_point2 = new Point(400, 400);

    private int singleWaveWidth = 600;
    private int halfWaveWidth;
    private int halfWaveHeight = 100;
    private int dx;

    public PathWavyLineView(Context context) {
        super(context);
    }

    public PathWavyLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        path.moveTo(100, 300);


        path.quadTo(200, 200, 300, 300);
        path.quadTo(400, 400, 500, 300); //这是一条小波浪

//        path2.rQuadTo(, , 200,100 );
//        path.rQuadTo(dx1,dy1,dx2,dy2);
        //dx1:控制点X坐标，表示相对上一个终点X坐标的位移坐标，可为负值，正值表示相加，负值表示相减；
        //dy1:控制点Y坐标，相对上一个终点Y坐标的位移坐标。同样可为负值，正值表示相加，负值表示相减；
        //dx2:终点X坐标，同样是一个相对坐标，相对上一个终点X坐标的位移值，可为负值，正值表示相加，负值表示相减；
        //dy2:终点Y坐标，同样是一个相对，相对上一个终点Y坐标的位移值。可为负值，正值表示相加，负值表示相减

        path2.moveTo(100, 350);
        path2.rQuadTo(100, -150, 200, 0);
        path2.rQuadTo(100, 150, 200, 0);

        halfWaveWidth = singleWaveWidth >> 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawPoint(point1.x, point1.y, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPoint(point2.x, point2.y, mPaint);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawPoint(point3.x, point3.y, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(c_point1.x, c_point1.y, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(c_point2.x, c_point2.y, mPaint);

        mPaint.setColor(Color.CYAN);
        canvas.drawPath(path2, mPaint);

        path3.reset();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        path3.moveTo(-singleWaveWidth + dx, 500);

        for (int i = -singleWaveWidth; i <= getWidth() + singleWaveWidth; i += singleWaveWidth) {
            path3.rQuadTo(halfWaveWidth >> 1, -halfWaveHeight, halfWaveWidth, 0);
            path3.rQuadTo(halfWaveWidth >> 1, halfWaveHeight, halfWaveWidth, 0);
        }
        path3.lineTo(getWidth(),getHeight());
        path3.lineTo(0,getHeight());
        path3.close();
        canvas.drawPath(path3, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startAnim();
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, singleWaveWidth);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(animation -> {
            dx = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        animator.start();
    }

}
