package com.dan.learn.lab2.widget.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

public class MultiTouchView2 extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    private float offsetX;
    private float offsetY;
    private float originalOffsetX;
    private float originalOffsetY;
    private float downX;
    private float downY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bac_shortcut);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0, sumY = 0;
        boolean actionUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        int pointerCount = event.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            if (!(actionUp && event.getActionIndex() == i)) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }

        if (actionUp) {
            pointerCount -= 1;
        }

        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = originalOffsetX + focusX - downX;
                offsetY = originalOffsetY + focusY - downY;
                invalidate();
                break;
        }
        return true;
    }
}
