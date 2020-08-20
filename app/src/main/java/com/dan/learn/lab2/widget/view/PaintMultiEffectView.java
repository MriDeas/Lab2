package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/8/10
 * Description:
 * Modify time:
 */
public class PaintMultiEffectView extends View {

    private PorterDuff.Mode[] X_FER_MODES = new PorterDuff.Mode[]{
            PorterDuff.Mode.ADD, PorterDuff.Mode.DARKEN, PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.DST_IN, PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.DST_OVER, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.OVERLAY, PorterDuff.Mode.SCREEN, PorterDuff.Mode.SRC,
            PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.SRC_IN, PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.XOR};

    private String[] X_FER_MODE_STR_ARR = new String[]{
            "ADD", "DARKEN", "DST", "DST_ATOP",
            "DST_IN", "DST_OUT", "DST_OVER",
            "LIGHTEN", "MULTIPLY", "OVERLAY",
            "SCREEN", "SRC", "SRC_ATOP",
            "SRC_IN", "SRC_OVER", "XOR"};

    public static final int MODE_SHADOW_LAYER = 1;
    public static final int MODE_SHADER = 2;
    public static final int MODE_X_FER_MODE = 3;
    public static final int MODE_BLEND_MODE = 4;
    public static final int MODE_MASK_FILTER = 5;

    private Paint mPaint;
    private MaskFilter innerMaskFilter;
    private BlurMaskFilter normalMaskFilter;
    private BlurMaskFilter outerMaskFilter;
    private BlurMaskFilter solidMaskFilter;
    private Bitmap bitmap;
    private int mode;
    private EmbossMaskFilter embossMaskFilter;
    private EmbossMaskFilter embossMaskFilter2;
    private EmbossMaskFilter embossMaskFilter3;
    private EmbossMaskFilter embossMaskFilter4;
    private EmbossMaskFilter embossMaskFilter5;
    private EmbossMaskFilter embossMaskFilter6;
    private EmbossMaskFilter embossMaskFilter7;
    private EmbossMaskFilter embossMaskFilter8;
    private EmbossMaskFilter embossMaskFilter9;
    private EmbossMaskFilter embossMaskFilter10;
    private BitmapShader bitmapShader1;
    private BitmapShader bitmapShader2;
    private BitmapShader bitmapShader3;

    private Xfermode xfermode;
    private Xfermode currMode;
    private int XFER_MODE_INDEX = 0;
    private String xferModeStr;

    private int width = 200;
    private int height = 200;
    private Bitmap dstBitmap;
    private Bitmap srcBitmap;

    public PaintMultiEffectView(Context context) {
        this(context, null);
    }

    public PaintMultiEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaintMultiEffectView);
        mode = a.getInteger(R.styleable.PaintMultiEffectView_mode, MODE_MASK_FILTER);
        a.recycle();

//        if (mode == MODE_X_FER_MODE) {
//            xferModeStr = X_FER_MODE_STR_ARR[XFER_MODE_INDEX];
//            xfermode = new PorterDuffXfermode(X_FER_MODES[XFER_MODE_INDEX]);
//            setOnClickListener(v -> {
//                if (XFER_MODE_INDEX >= X_FER_MODES.length) {
//                    XFER_MODE_INDEX = 0;
//                }
//                xfermode = new PorterDuffXfermode(X_FER_MODES[XFER_MODE_INDEX]);
//                xferModeStr = X_FER_MODE_STR_ARR[XFER_MODE_INDEX];
//                invalidate();
//                XFER_MODE_INDEX++;
//            });
//        }
    }

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        innerMaskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER);
        normalMaskFilter = new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL);
        outerMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.OUTER);
        solidMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID);

        //float[] 光源方向  ambient 光亮度  specular 反射强度  blur radius 模糊范围
        embossMaskFilter = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.67f, 6, 15);
        embossMaskFilter2 = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.2f, 6, 15);
        embossMaskFilter3 = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.3f, 36, 15);
        embossMaskFilter4 = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.57f, 36, 35);
        embossMaskFilter5 = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.9f, 36, 35);

        embossMaskFilter6 = new EmbossMaskFilter(new float[]{30, 1, 1},
                0.9f, 36, 35);

        embossMaskFilter7 = new EmbossMaskFilter(new float[]{1, 30, 1},
                0.9f, 16, 35);

        embossMaskFilter8 = new EmbossMaskFilter(new float[]{1, 1, 10},
                0.6f, 16, 35);

        embossMaskFilter9 = new EmbossMaskFilter(new float[]{30, 31, 0},
                0.6f, 16, 35);

        embossMaskFilter10 = new EmbossMaskFilter(new float[]{30, 31, 60},
                0.6f, 16, 35);

//        mPaint.setShader(new BitmapShader());

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_mi);
        bitmapShader1 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
        bitmapShader2 = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        bitmapShader3 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        dstBitmap = makeDst(width, height);
        srcBitmap = makeSrc(width, height);
    }

    public void setMode(@IntRange(from = 1, to = 5) int mode) {
        this.mode = mode;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mode == MODE_MASK_FILTER) {
            reset();
            drawMaskFilter1(canvas);
            drawMaskFilter2(canvas);
        } else if (mode == MODE_SHADOW_LAYER) {
            reset();
            drawShadowLayer(canvas);
        } else if (mode == MODE_SHADER) {
            reset();
            drawShader1(canvas);
        } else if (mode == MODE_BLEND_MODE) {
            drawBlendMode(canvas);
        } else if (mode == MODE_X_FER_MODE) {
            drawXferMode(canvas);
        }
    }

    private void reset() {
        mPaint.setShader(null);
        mPaint.setMaskFilter(null);
        mPaint.setXfermode(null);
    }

    public void setXFerPorterDuffMode(Xfermode mode) {
        currMode = mode;
        invalidate();
    }

    private void drawXferMode(Canvas canvas) {
        int saveCount = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawRect(200, 100, 300, 200, mPaint);

        mPaint.setXfermode(currMode);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(300, 200, 50, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveCount);

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
//        canvas.drawText(xferModeStr, 5, 100, mPaint);
        canvas.drawText("点击切换模式", 5, 150, mPaint);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawText("矩形 :SRC", 500, 100, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("圆形 :DST", 500, 200, mPaint);
    }

    private void drawBlendMode(Canvas canvas) {

    }

    private void drawShader1(Canvas canvas) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); //关闭硬件加速
        canvas.drawColor(Color.DKGRAY);

        canvas.translate(20, 20);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setShader(bitmapShader1);
        canvas.drawRect(0, 0, 200, 230, mPaint);

        canvas.translate(230, 0);
        mPaint.setShader(bitmapShader2);
        canvas.drawRect(0, 0, 200, 200, mPaint);

        canvas.translate(260, 10);
        mPaint.setShader(bitmapShader3);
        canvas.drawRect(0, 0, 200, 200, mPaint);
    }

    private void drawShadowLayer(Canvas canvas) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); //关闭硬件加速
        canvas.drawColor(Color.DKGRAY);

        mPaint.setColor(Color.RED);
        mPaint.setShadowLayer(10, 10, 10, Color.WHITE);
        canvas.drawCircle(50, 100, 30, mPaint);

        mPaint.setColor(Color.CYAN);
        mPaint.setShadowLayer(20, 10, 10, Color.WHITE);
        canvas.drawCircle(120, 100, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setShadowLayer(20, 20, 10, Color.WHITE);
        canvas.drawCircle(200, 100, 30, mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setShadowLayer(20, 10, 20, Color.WHITE);
        canvas.drawCircle(300, 100, 30, mPaint);

        mPaint.setColor(Color.GREEN);
        mPaint.setShadowLayer(20, 0, 0, Color.WHITE);
        canvas.drawCircle(400, 100, 30, mPaint);

        mPaint.setColor(Color.MAGENTA);
        mPaint.setShadowLayer(20, -10, -20, Color.WHITE);
        canvas.drawCircle(500, 100, 30, mPaint);

        mPaint.setColor(Color.MAGENTA);
        mPaint.setShadowLayer(6, -10, -20, Color.WHITE);
        canvas.drawCircle(600, 100, 30, mPaint);
    }

    private void drawMaskFilter2(Canvas canvas) {
        mPaint.setMaskFilter(embossMaskFilter);
        canvas.drawBitmap(bitmap, 60, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter2);
        canvas.drawBitmap(bitmap, 160, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter3);
        canvas.drawBitmap(bitmap, 280, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter4);
        canvas.drawBitmap(bitmap, 390, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter5);
        canvas.drawBitmap(bitmap, 490, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter6);
        canvas.drawBitmap(bitmap, 570, 220, mPaint);

        mPaint.setMaskFilter(embossMaskFilter7);
        canvas.drawBitmap(bitmap, 60, 310, mPaint);

        mPaint.setMaskFilter(embossMaskFilter8);
        canvas.drawBitmap(bitmap, 160, 310, mPaint);

        mPaint.setMaskFilter(embossMaskFilter9);
        canvas.drawBitmap(bitmap, 280, 310, mPaint);

        mPaint.setMaskFilter(embossMaskFilter10);
        canvas.drawBitmap(bitmap, 390, 310, mPaint);

    }

    private void drawMaskFilter1(Canvas canvas) {
        mPaint.setTextSize(20);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("BlurMaskFilter.Blur", 70, 20, mPaint);

        mPaint.setMaskFilter(innerMaskFilter); //仅在图案内部制造模糊
        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(50, 50, 150, 150, mPaint);
        canvas.drawCircle(100, 100, 30, mPaint);
//        canvas.drawBitmap(bitmap, 50, 220, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setMaskFilter(normalMaskFilter); //图案边界模糊
//        canvas.drawRect(200, 50, 300, 150, mPaint);
        canvas.drawCircle(260, 100, 30, mPaint);
//        canvas.drawBitmap(bitmap, 200, 220, mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setMaskFilter(outerMaskFilter); //图案内部颜色不变，采用原色，边界模糊
//        canvas.drawRect(350, 50, 450, 150, mPaint);
        canvas.drawCircle(420, 100, 30, mPaint);
//        canvas.drawBitmap(bitmap, 300, 220, mPaint);

        mPaint.setColor(Color.CYAN);
        mPaint.setMaskFilter(solidMaskFilter); //内部使用设置颜色填充，边界模糊
//        canvas.drawRect(500, 50, 600, 150, mPaint);
        canvas.drawCircle(580, 100, 30, mPaint);
//        canvas.drawBitmap(bitmap, 520, 220, mPaint);

        mPaint.setColor(Color.BLACK);
        String text1 = "Inner";
        String text2 = "Normal";
        String text3 = "Outer";
        String text4 = "Solid";

        float w1 = mPaint.measureText(text1);
        float w2 = mPaint.measureText(text2);
        float w3 = mPaint.measureText(text3);
        float w4 = mPaint.measureText(text4);

        canvas.drawText(text1, 100 - (w1 / 2), 180, mPaint);
        canvas.drawText(text2, 260 - (w2 / 2), 180, mPaint);
        canvas.drawText(text3, 420 - (w3 / 2), 180, mPaint);
        canvas.drawText(text4, 580 - (w4 / 2), 180, mPaint);
    }

    //定义一个绘制圆形Bitmap的方法
    private Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF26AAD1);
        c.drawOval(new RectF(0, 0, w * 3 >> 2, h * 3 >> 2), p);
        return bm;
    }

    //定义一个绘制矩形的Bitmap的方法
    private Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCE43);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

}
