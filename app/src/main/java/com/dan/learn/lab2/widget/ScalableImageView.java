package com.dan.learn.lab2.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/10/10
 * Description:
 * Modify time:
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    // 两种大小尺寸 双击切换 尺寸
    // 支持在范围内滑动
    // 尺寸切换 有动画效果

    public static final float OVER_SCALE_FACTOR = 1.5f;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final GestureDetectorCompat detector;

    private Bitmap mBitmap;
    private float originalOffsetX;
    private float originalOffsetY;
    private float offsetX;
    private float offsetY;

    private float smallScale;
    private float bigScale;
    private boolean isBig;
    private float currentScale;
    private ObjectAnimator scaleAnimation;
    private OverScroller mScroller;

    private ScaleGestureDetector scaleGestureDetector;
    private ScalableRunnable mGestureRunnable = new ScalableRunnable();


    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        detector = new GestureDetectorCompat(getContext(), this);
        detector.setOnDoubleTapListener(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_gifts);
        mScroller = new OverScroller(context);
        //实现双指 缩放
        scaleGestureDetector = new ScaleGestureDetector(context, new ScalableGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if(!scaleGestureDetector.isInProgress()){
            result = detector.onTouchEvent(event);
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图片宽高 View宽高
        float bWidth = mBitmap.getWidth();
        float bHeight = mBitmap.getHeight();

        float width = getWidth();
        float height = getHeight();


        if (bWidth / bHeight > width / height) {
            smallScale = width / bWidth;
            bigScale = height / bHeight * OVER_SCALE_FACTOR;
        } else {
            smallScale = height / bHeight;
            bigScale = width / bWidth * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
        originalOffsetX = (getWidth() - bWidth) / 2;
        originalOffsetY = (getHeight() - bHeight) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, originalOffsetX, originalOffsetY, mPaint);
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimation == null)
            scaleAnimation = ObjectAnimator.ofFloat(this, "currentScale", 0);
        scaleAnimation.setFloatValues(smallScale, bigScale);
        return scaleAnimation;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    private void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent downE, MotionEvent moveE, float distanceX, float distanceY) {
        //distanceX 向左划 是正值 向右划 是负值
        //distanceY 向上划 是正值 向下划 是负值
        if (isBig) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            //偏移范围 （图片宽度 - View宽度）/ 2
            float halfW = (mBitmap.getWidth() * bigScale - getWidth()) / 2;
            float halfH = (mBitmap.getHeight() * bigScale - getHeight()) / 2;
            offsetX = Math.min(offsetX, halfW);
            offsetX = Math.max(offsetX, -halfW);
            offsetY = Math.min(offsetY, halfH);
            offsetY = Math.max(offsetY, -halfH);
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isBig) {
            int halfW = (int) ((mBitmap.getWidth() * bigScale - getWidth()) / 2);
            int halfH = (int) ((mBitmap.getHeight() * bigScale - getHeight()) / 2);

            mScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, -halfW, halfW, -halfH, halfH, 100, 100);
            postOnAnimation(mGestureRunnable);
        }
        return false;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        isBig = !isBig;
        if (isBig) {
            float touchX = e.getX() - (getWidth() >> 1);
            float scaleX = (e.getX() - (getWidth() >> 1)) * bigScale / smallScale;
            float touchY = e.getY() - (getHeight() >> 1);
            float scaleY = (e.getY() - (getHeight() >> 1)) * bigScale / smallScale;

            offsetX = touchX - scaleX;
            offsetY = touchY - scaleY;
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private class ScalableGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        private float initialScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = initialScale * detector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }


    private class ScalableRunnable implements Runnable {
        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                offsetX = mScroller.getCurrX();
                offsetY = mScroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }

}
