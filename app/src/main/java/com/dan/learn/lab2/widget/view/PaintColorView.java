package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
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
            PorterDuff.Mode.XOR, //和 CLEAR一样，清除
            PorterDuff.Mode.DARKEN, //变暗
            PorterDuff.Mode.LIGHTEN, //变亮
            PorterDuff.Mode.MULTIPLY, // 正片叠加
            PorterDuff.Mode.SCREEN, //滤色
            PorterDuff.Mode.ADD, //饱和度相加
            PorterDuff.Mode.OVERLAY //叠加
    };
    private String[] modeStr = {
            "Clear", "SRC", "DST", "SRC_OVER", "DST_OVER", "SRC_IN",
            "DST_IN", "SRC_OUT", "DST_OUT", "SRC_ATOP", "DST_ATOP", "XOR", "DARKEN",
            "LIGHTEN", "MULTIPLY", "SCREEN","ADD"
    };

    private static final int FILTER_MODE_LIGHT = 1;
    private static final int FILTER_PORTER_DUFF = 2;
    private static final int FILTER_COLOR_MATRIX = 3;

    private PorterDuffColorFilter porterDuffColorFilter;

    private Paint mPaint;

    private RectF rectF = new RectF();

    private Rect rectF2 = new Rect();

    private int filterMode;
    private Bitmap bitmap;
    private int porterDuffColorFilterIndex;
    private ColorFilter lightColorFilter;
    private Bitmap bitmap_cat;

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
            bitmap_cat = BitmapFactory.decodeResource(getResources(), R.drawable.pic_cat);
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
        //PorterDuffColorFilter 只能完成与一个特定颜色值合成
        canvas.save();
        mPaint.setTextSize(20);

        rectF2.set(400, 60, 600, 200);
        canvas.drawBitmap(bitmap_cat, null,rectF2, mPaint);
        mPaint.setColorFilter(porterDuffColorFilter);

        rectF2.set(700, 60, 900, 300);
        canvas.drawBitmap(bitmap_cat, null,rectF2,mPaint);
        mPaint.setColorFilter(null);
        canvas.restore();

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
        mPaint.setColor(Color.rgb(0,255, 0));
        ColorFilter colorFilter = mPaint.getColorFilter();
        mPaint.setColorFilter(colorFilter);
        rectF.set(230, 10, 360, 300);
        canvas.drawRect(rectF, mPaint);

        mPaint.setColorFilter(null);
        rectF.set(600, 10, 800, 300);
        canvas.drawRect(rectF, mPaint);
    }

    private void drawLightColorFilterBitmap(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        mPaint.setColorFilter(getLightColorFilter(0xffffff,0x00f000));
        canvas.drawBitmap(bitmap, 380, 10, mPaint);
    }

    private ColorFilter getLightColorFilter(@ColorInt int mul, @ColorInt int add){
        if(lightColorFilter == null){
            lightColorFilter = new LightingColorFilter(mul, add);
        }
        return lightColorFilter;
    }

    public PorterDuff.Mode[] getPorterColorModes() {
        return modes;
    }
}
