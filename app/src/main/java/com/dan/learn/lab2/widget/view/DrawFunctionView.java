package com.dan.learn.lab2.widget.view;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.utils.SizeUtil;

/**
 * Created by: dan
 * Created time: 2020/8/31
 * Description:
 * Modify time:
 */
public class DrawFunctionView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mSolidCirclePath = new Path();

    private Region mSolidCircleRegion = new Region();

    private RectF mArcRectF = new RectF();
    private Rect mTextRect = new Rect();

    private int centerX;
    private int centerY;
    private int mWidth, mHeight;

    private float mSolidCircleRadius = SizeUtil.dp2px(80);

    private static final int MAX_PROGRESS = 100;
    private int mProgress;
    private boolean hideTouchIndicator = true;


    public DrawFunctionView(Context context) {
        super(context);
    }

    public DrawFunctionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w >> 1;
        centerY = h >> 1;
        mWidth = w;
        mHeight = h;
        init();
    }

    {
        mPaint.setStrokeWidth(SizeUtil.dp2px(15));
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    private void init() {
        mSolidCirclePath.reset();
        mSolidCirclePath.addCircle(centerX, centerY, mSolidCircleRadius, Path.Direction.CW);
        Region region = new Region(0, 0, mWidth, mHeight);
        mSolidCircleRegion.setPath(mSolidCirclePath, region);

        postDelayed(this::animDrawProgress, 2000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawSolidCircle(canvas);
        drawStatus(canvas);
        drawProgressElement(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mSolidCircleRegion.contains(x, y)) {
                    //判断隐藏指针
                    hideTouchIndicator = true;
                    delayInvalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mSolidCircleRegion.contains(x, y)) {
                    //判断旋转

                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (mSolidCircleRegion.contains(x, y)) {
                    //判断显示指针
                    hideTouchIndicator = false;
                    delayInvalidate();
                }
                break;
        }
        return true;
    }

    private void delayInvalidate() {
        postDelayed(this::invalidate, 300);
    }

    private void drawStatus(Canvas canvas) {
        //进行中 已完成 已延迟 已放弃

        String statusText = "进行中..";
        mPaint.setTextSize(50);
        mPaint.setFakeBoldText(true);
        mPaint.getTextBounds(statusText, 0, statusText.length(), mTextRect);
        mPaint.setColor(Color.WHITE);
        int s_height = mTextRect.height();
        int s_width = mTextRect.width();
        int sX = centerX - s_width / 2;
        int sY = centerY + s_height;
        canvas.drawText(statusText, sX, sY, mPaint);
    }

    private void drawProgressElement(Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.setElegantTextHeight(true);
        }

        float strokeWidth = mPaint.getStrokeWidth();
        float offset = strokeWidth - 10;
        float l = centerX - mSolidCircleRadius - offset;
        float t = centerY - mSolidCircleRadius - offset;
        float r = centerX + mSolidCircleRadius + offset;
        float b = centerY + mSolidCircleRadius + offset;

        float angle = (float) mProgress / (float) MAX_PROGRESS * (float) 360;
        mArcRectF.set(l, t, r, b);
        canvas.drawArc(mArcRectF, 270, angle, false, mPaint);

        String progressText = mProgress + "%";
        mPaint.setTextSize(67);
        mPaint.setColor(Color.WHITE);
        mPaint.getTextBounds(progressText, 0, progressText.length(), mTextRect);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFakeBoldText(true);

        int p_textWidth = mTextRect.width();
        int p_textHeight = mTextRect.height();

        float progressX = centerX - (p_textWidth - 30);
        float progressY = centerY - (p_textHeight - 30);
        float progressXEnd = progressX + p_textWidth;
        canvas.drawText(progressText, progressX, progressY, mPaint);

        mPaint.setTextSize(20);
        mPaint.setFakeBoldText(false);
        mPaint.setColor(Color.GREEN);
        String timeText = "60 min";
        mPaint.getTextBounds(timeText, 0, timeText.length(), mTextRect);
        int timeHeight = mTextRect.height();

        float timeX = progressXEnd + 15;
        float timeY = centerY + timeHeight - 35;
        canvas.drawText(timeText, timeX, timeY, mPaint);
    }

    private void animDrawProgress() {
        ValueAnimator animator = ValueAnimator.ofInt(70);
        animator.setDuration(3300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            mProgress = (int) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    private void drawSolidCircle(Canvas canvas) {
        mSolidCircleRegion.getBounds();
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mSolidCirclePath, mPaint);

        if (!hideTouchIndicator) {
            mPaint.setColor(Color.argb(55,100,100,100));
            canvas.drawCircle(centerX, centerY - 120, 25, mPaint);
        }
        mPaint.setAlpha(255);
    }


    private void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
