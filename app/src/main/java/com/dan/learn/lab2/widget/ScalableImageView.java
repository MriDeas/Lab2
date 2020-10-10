package com.dan.learn.lab2.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/10/10
 * Description:
 * Modify time:
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener {

    // 两种大小尺寸 双击切换 尺寸
    // 支持在范围内滑动
    // 尺寸切换 有动画效果

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final GestureDetectorCompat detector;

    private Bitmap mBitmap;
    private float offsetX;
    private float offsetY;

    private float smallScale;
    private float bigScale;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        detector = new GestureDetectorCompat(getContext(), this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bac_panda);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图片宽高 View宽高
        float bWidth = mBitmap.getWidth();
        float bHeight = mBitmap.getHeight();



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
