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

public class TouchView extends View {


    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Bitmap bitmap;
    private float offsetX;
    private float offsetY;

    private float downX;
    private float downY;

    private float originalOffsetX;
    private float originalOffsetY;
    private int trackedPointerId;

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bac_shortcut);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackedPointerId = event.getPointerId(0);
                downX = event.getX();
                downY = event.getY();

                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = event.findPointerIndex(trackedPointerId);
                offsetX = originalOffsetX + (event.getX(pointerIndex) - downX);
                offsetY = originalOffsetY + (event.getY(pointerIndex) - downY);
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionDownIndex = event.getActionIndex();
                trackedPointerId = event.getPointerId(actionDownIndex);
                downX = event.getX(actionDownIndex);
                downY = event.getY(actionDownIndex);
                originalOffsetX = offsetX;
                originalOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int actionUpIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionUpIndex);
                int newIndex;
                if (pointerId == trackedPointerId) {
                    if (actionUpIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }

                    trackedPointerId = event.getPointerId(newIndex);
                    downX = event.getX(actionUpIndex);
                    downY = event.getY(actionUpIndex);
                    originalOffsetX = offsetX;
                    originalOffsetY = offsetY;
                }

                break;
        }
        return true;
    }


}
