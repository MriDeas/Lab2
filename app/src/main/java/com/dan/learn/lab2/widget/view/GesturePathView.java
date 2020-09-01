package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GesturePathView extends View {

    private Path path = new Path();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GesturePathView(Context context) {
        super(context);
    }

    public GesturePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(35);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
    }

    private float mPreX, mPreY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                mPreX = x;
                mPreY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
//                path.lineTo(x, y); //线段会比较有棱角
                float endX = (mPreX + x) / 2;
                float endY = (mPreY + y) / 2;
                path.quadTo(mPreX, mPreY, endX, endY);
                mPreX = x;
                mPreY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                postDelayed(() -> {
                    path.reset();
                    invalidate();
                }, 2000);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("绘制手势图线，试试，很easy简单", 100, 100, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
    }


}
