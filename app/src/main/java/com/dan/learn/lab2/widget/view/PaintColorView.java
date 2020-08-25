package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintColorView extends View {

    private PorterDuff.Mode[] modes = {
            PorterDuff.Mode.CLEAR,
            PorterDuff.Mode.SRC,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.SRC_IN,
            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.DST_ATOP,
            PorterDuff.Mode.XOR,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.SCREEN
    };
    private String[] modeStr = {
            "Clear", "SRC", "DST", "SRC_OVER", "DST_OVER", "SRC_IN",
            "DST_IN", "SRC_OUT", "DST_OUT", "SRC_ATOP", "DST_ATOP", "XOR", "DARKEN",
            "LIGHTEN", "MULTIPLY", "SCREEN"
    };

    private static final int FILTER_MODE_LIGHT = 1;
    private static final int FILTER_PORTER_DUFF = 2;
    private static final int FILTER_COLOR_MATRIX = 3;

    private PorterDuffColorFilter porterDuffColorFilter;

    private Paint mPaint;

    private RectF rectF = new RectF();

    private RectF rectF2 = new RectF();

    private int filterMode;
    private Bitmap bitmap;
    private int porterDuffColorFilterIndex;

    public PaintColorView(Context context) {
        this(context, null);
    }

    public PaintColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        }
        if (filterMode == FILTER_MODE_LIGHT) {
            drawRoundRect(canvas);
            drawLightColorFilterRect(canvas);
            drawLightColorFilterBitmap(canvas);
        } else if (filterMode == FILTER_PORTER_DUFF) {
            drawPorterDuffColorFilter(canvas);
        } else if (filterMode == FILTER_COLOR_MATRIX) {
            drawColorMatrixFilter(canvas);
        }
    }

    private void drawPorterDuffColorFilter(Canvas canvas) {
        mPaint.setTextSize(20);
//        rectF.set(400, 20, 500, 120);
        rectF2.set(460, 60, 560, 160);
//        int count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmap,400,20,mPaint);
//        canvas.drawRect(rectF, mPaint);
        mPaint.setColorFilter(porterDuffColorFilter);
        canvas.drawRect(rectF2, mPaint);

        mPaint.setColorFilter(null);
//        canvas.restoreToCount(count);



//        mPaint.setColor(Color.RED);
//        canvas.drawRect(rectF, mPaint);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRect(rectF2, mPaint);
        mPaint.setTextSize(20);
        canvas.drawText(modeStr[porterDuffColorFilterIndex], 10, 20, mPaint);
    }

    private void drawColorMatrixFilter(Canvas canvas) {
        mPaint.setColor(Color.RED);
        rectF.set(100, 20, 300, 320);
        canvas.drawRect(rectF, mPaint);
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setARGB(100, 99, 100, 102);
        canvas.drawRect(10, 10, 200, 300, mPaint);
    }

    public void setLightColorFilter(ColorFilter filter) {
        filterMode = FILTER_MODE_LIGHT;
        if (mPaint != null) {
            mPaint.setColorFilter(filter);
        }
        invalidate();
    }

    public void setPorterDuffColorFilter(PorterDuffColorFilter filter, int index) {
        filterMode = FILTER_PORTER_DUFF;
        porterDuffColorFilter = filter;
        porterDuffColorFilterIndex = index;
        invalidate();
    }

    public void setMatrixColorFilter(ColorMatrixColorFilter matrixColorFilter) {
        filterMode = FILTER_COLOR_MATRIX;
        if (mPaint != null) {
            mPaint.setColorFilter(matrixColorFilter);
        }
        invalidate();
    }

    private void drawLightColorFilterRect(Canvas canvas) {
        rectF.set(230, 10, 360, 300);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.parseColor("#326b6b"));
    }

    private void drawLightColorFilterBitmap(Canvas canvas) {
        canvas.drawBitmap(bitmap, 380, 10, mPaint);
    }

    public PorterDuff.Mode[] getPorterColorModes() {
        return modes;
    }
}
