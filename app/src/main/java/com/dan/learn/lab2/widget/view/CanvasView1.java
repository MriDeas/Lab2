package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CanvasView1 extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmapCanvas;

    private Canvas realBitmapCanvas;

    public CanvasView1(Context context) {
        super(context);
    }

    public CanvasView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(30);
        bitmapCanvas = Bitmap.createBitmap(500, 300, Bitmap.Config.ARGB_8888);
        realBitmapCanvas = new Canvas(bitmapCanvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasSaveLayer(canvas);
        getCanvas1(canvas);
        getCanvas2(canvas);
    }


    private void canvasSaveLayer(Canvas canvas) {
        canvas.save();
        canvas.drawColor(Color.CYAN);
        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(630, 10, 1100, 900, mPaint);

        int saveLayerId1 = canvas.saveLayer(700, 50, 1000, 800, mPaint);
        canvas.drawColor(Color.RED);
        int alphaLayerId = canvas.saveLayerAlpha(700, 500, 1000, 800, 99); //新建图层，透明度0~255
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawCircle(700, 500, 100, mPaint);
//        canvas.restoreToCount(alphaLayerId);

        int lastLayerId = canvas.saveLayer(700, 650, 1000, 800, mPaint);
//        canvas.drawCircle(850, 150, 100, mPaint);
        canvas.drawColor(Color.YELLOW);

        canvas.restore();
        //结论 canvas.
        //canvas.save

    }

    private void getCanvas1(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(100, 10, 600, 310, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("获得canvas1 from View", 110, 50, mPaint);
        canvas.restore();
    }

    private void getCanvas2(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.MAGENTA);
        realBitmapCanvas.drawRect(0, 0, 500, 300, mPaint);
        mPaint.setColor(Color.WHITE);
        realBitmapCanvas.drawText("获得Canvas2 from Bitmap", 110, 50, mPaint);
        canvas.drawBitmap(bitmapCanvas, 100, 400, mPaint);
        canvas.restore();
    }
}
