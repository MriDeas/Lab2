package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasBasicView extends View {

    private Paint mPaint;
    private Bitmap bitmap;
    private Path mPath,mPath2;

    public CanvasBasicView(Context context) {
        this(context, null);
    }

    public CanvasBasicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mPath2 = new Path();
        mPath.addCircle(200, 700, 80, Path.Direction.CW);
        mPath2.addCircle(750, 850, 80, Path.Direction.CW);
//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasClipApi(canvas);
        canvasClipPathApi(canvas);
//        canvas.clipRect();
//        canvas.clipOutRect();
//
//        canvas.setMatrix();
//        canvas.setBitmap();
//        canvas.setDensity();
//        canvas.setDrawFilter();
//
//        canvas.skew();
//        canvas.concat();
//
//        canvas.enableZ();
//        canvas.disableZ();
//
//        canvas.isOpaque();
//        canvas.isHardwareAccelerated();
    }

    private void canvasClipApi(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
//        if(bitmap == null || bitmap.isRecycled()){
//            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        }
        canvas.save();
        //划定一片区域，仅在该区域内部绘制内容
        boolean success = canvas.clipRect(100, 100, 350, 350);
        canvas.drawRect(100, 100, 350, 350, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 50, mPaint);

        canvas.drawRect(200, 250, 600, 350, mPaint);
        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(200, 350, 350, 450, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(200, 250, 600, 350, mPaint);

        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("clip rect", 600, 300, mPaint);

        mPaint.setColor(Color.GRAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //clipOutRect 划出一片区域，仅能在区域外围绘制内容
            canvas.clipOutRect(610, 100, 910, 350);
            canvas.drawCircle(760, 250, 230, mPaint);
            mPaint.setColor(Color.CYAN);
            canvas.drawText("clip out rect", 620, 380, mPaint);
        }
    }

    private void canvasClipPathApi(Canvas canvas) {

        //clipPath 以path划定一个区域，仅在path区域内部绘制内容
        mPaint.setColor(Color.argb(100,100,20, 150));
        canvas.drawRect(100, 600, 350, 800, mPaint);
        mPaint.setColor(Color.CYAN);
        int saveLayer = canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.clipPath(mPath);
        canvas.drawRect(100, 600, 350, 800, mPaint);
        canvas.restoreToCount(saveLayer);

        mPaint.setColor(Color.BLACK);
        canvas.drawText("clip path circle ", 110,810, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int saveLayer1 = canvas.saveLayer(null, mPaint);
            canvas.clipOutPath(mPath2);
            mPaint.setColor(Color.DKGRAY);
            canvas.drawCircle(800, 900, 200, mPaint);
            mPaint.setColor(Color.WHITE);
            canvas.drawText("canvas clip out path", 666, 999,mPaint);
            canvas.restoreToCount(saveLayer1);
        }

    }

    private void canvasSkew(Canvas canvas) {
        canvas.skew(0.1f, 0.25f);

    }

    private void canvasConcat(Canvas canvas) {

    }

    private void canvasSetMatrix(Canvas canvas) {

    }

    private void canvasSetBitmap(Canvas canvas) {

    }

    private void canvasDensity(Canvas canvas) {

    }

    private void canvasDrawFilter(Canvas canvas) {

    }


}
