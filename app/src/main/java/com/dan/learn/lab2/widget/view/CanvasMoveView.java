package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

public class CanvasMoveView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float skewX;
    private float skewOffsetX = 100;
    private float rotateX;
    private float rotateOffsetX = 200;
    private float scaleX;
    private float scaleOffsetX = 150;
    private Bitmap bitmap;

    public CanvasMoveView(Context context) {
        super(context);
    }

    public CanvasMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        skewX(canvas, 200, true);
        skewX(canvas, 400, false);
        rotate(canvas);
        scale(canvas);
        translate(canvas);
        canvasBitmap(canvas);
    }

    private void canvasBitmap(Canvas canvas) {
        if (bitmap != null && !bitmap.isRecycled()) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);

        }
    }

    private void translate(Canvas canvas) {
        //translate 后，坐标没有改变，但是相对位置发生变化；X轴和Y轴分别需要加上位移的距离
        int saveLayer = canvas.saveLayer(300, 1000, 800, 1260, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(310, 1010, 10, mPaint);
        canvas.translate(300, 100);
//        canvas.drawLine();
        canvas.drawColor(Color.argb(100, 100, 200, 255));
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(310, 1010, 10, mPaint);
        canvas.drawText("translate X/Y", 100, 1060, mPaint);
        canvas.restoreToCount(saveLayer);
    }


    private void scale(Canvas canvas) {
        //canvas X轴方向或Y轴方向缩放，缩放会使目标物
        mPaint.setColor(Color.DKGRAY);
        scaleX = 100;
        canvasScale(canvas, 0.8f, 0.8f, scaleX);
        scaleX += scaleOffsetX;
        canvasScale(canvas, 2f, 0.8f, scaleX);
        canvas.drawText("Canvas scaleX/Y", 100, 900, mPaint);
    }

    private void canvasScale(Canvas canvas, float scaleX, float scaleY, float x) {
        canvas.save();
        canvas.scale(scaleX, scaleY);
        canvas.drawRect(x, 800, x + 60, 900, mPaint);
        canvas.restore();
    }

    private void rotate(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        rotateX = 300;
        canvasRotate(canvas, 0, rotateX);
        rotateX += rotateOffsetX;
        canvasRotate(canvas, 10, rotateX);
        rotateX += rotateOffsetX;
        canvasRotate(canvas, 20, rotateX);
        rotateX += rotateOffsetX;
        canvasRotate(canvas, -5, rotateX);
        canvas.drawText("Canvas rotate", 100, 600, mPaint);
    }

    private void canvasRotate(Canvas canvas, int degree, float x) {
        //画板移动以X轴0度角顺时针旋转,旋转轴心位于（0,0）坐标
        canvas.save();
        canvas.rotate(degree);
        canvas.drawRect(x, 700, x + 60, 800, mPaint);
        canvas.restore();
        canvas.rotate(0);
    }

    private void skewX(Canvas canvas, float y, boolean inX) {

        mPaint.setColor(inX ? Color.RED : Color.MAGENTA);
        skewX = skewOffsetX;
        canvasSkew(canvas, 0, 0, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 0.3f : 0, inX ? 0 : 0.3f, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 0.5f : 0, inX ? 0 : 0.3f, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 0.7f : 0, inX ? 0 : 0.3f, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 0.9f : 0, inX ? 0 : 0.3f, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 1f : 0, inX ? 0 : 0.1f, skewX, y, inX);
        skewX += skewOffsetX;
        canvasSkew(canvas, inX ? 1.5f : 0, inX ? 0 : 0.3f, skewX, y, inX);
    }

    private void canvasSkew(Canvas canvas, float sx, float sy, float x, float y, boolean inX) {
        int saveLayer = canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.skew(sx, sy);
        canvas.drawRect(x, inX ? 100 : 300, x + 60, y, mPaint);
        canvas.restoreToCount(saveLayer);
        canvas.skew(0, 0);

        canvas.drawText("skew  错切,根据X和Y轴不同的错切系数 " + (inX ? "X轴" : "Y轴"), 100, y + 100, mPaint);
    }
}
