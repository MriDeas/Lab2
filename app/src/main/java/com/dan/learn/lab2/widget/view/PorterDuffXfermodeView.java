package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PorterDuffXfermodeView extends View {

    //原图 透明度标识为 Sa，颜色标识为 Sc; 目标图案 透明度标识为 Da,颜色标记为 Dc
    // ADD 模式， 公式： alpha = min(Sa + Da,1)  color = min(Sc + Dc,1)

    //Android 支持3层硬件加速，分别是 应用级 在 清单中设置；window级 设置window flag；View级，setLayerType()关闭
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect = new Rect();
    private PorterDuffXfermode addMode;
    private Bitmap srcBitmap;
    private Bitmap dstBitmap;
    private int width = 100;
    private int height = 100;
    private PorterDuffXfermode lightenMode;
    private PorterDuffXfermode darkenMode;
    private PorterDuffXfermode multiplyMode;
    private PorterDuffXfermode overlyMode;
    private PorterDuffXfermode srcMode;
    private PorterDuffXfermode srcInMode;
    private PorterDuffXfermode srcAtopMode;
    private PorterDuffXfermode srcOverMode;
    private PorterDuffXfermode srcOutMode;
    private PorterDuffXfermode dstOverMode;
    private PorterDuffXfermode dstOutMode;
    private PorterDuffXfermode dstInMode;
    private PorterDuffXfermode dstAtopMode;
    private PorterDuffXfermode dstMode;
    private PorterDuffXfermode screenMode;
    private Bitmap dst2Bitmap;
    private Bitmap src2Bitmap;
    private PorterDuffXfermode xorMode;

    public PorterDuffXfermodeView(Context context) {
        super(context);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setColor(Color.RED);
        mPaint2.setColor(Color.BLUE);
        mPaint2.setTextSize(30);

        addMode = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        darkenMode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        lightenMode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
        multiplyMode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        overlyMode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);

        srcMode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        srcInMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        srcOutMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        srcOverMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        srcAtopMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);

        dstMode = new PorterDuffXfermode(PorterDuff.Mode.DST);
        dstAtopMode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        dstInMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        dstOutMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        dstOverMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        screenMode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        xorMode = new PorterDuffXfermode(PorterDuff.Mode.XOR);

        srcBitmap = makeSrc(width, height);
        dstBitmap = makeDst(width, height);

        dst2Bitmap = makeDst2(width, height);
        src2Bitmap = makeSrc2(width, height);
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint2); //必须关闭硬件加速，否则某些不支持
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        colorOverlayMode(canvas);
    }


    private void colorOverlayMode(Canvas canvas) {

        mPaint2.setColor(Color.RED);
        //add 模式 颜色 ： Dst color = 0x FF0000  Src color = 0x0000FF = 0xff00ff 忽略计算透明度

        draw1(addMode, 0, 0, canvas, "addMode");
        draw1(darkenMode, 200, 0, canvas, "darken");
        draw1(lightenMode, 200, 0, canvas, "lighten");
        draw1(multiplyMode, 200, 0, canvas, "multiply");
        draw1(overlyMode, 200, 0, canvas, "overlay");

        draw1(srcMode, -800, 200, canvas, "Src");
        draw1(srcAtopMode, 200, 0, canvas, "srcAtop");
        draw1(srcInMode, 200, 0, canvas, "srcIn");
        draw1(srcOutMode, 200, 0, canvas, "srcOut");
        draw1(srcOverMode, 200, 0, canvas, "srcOver");

        draw1(dstMode, -800, 200, canvas, "dst");
        draw1(dstAtopMode, 200, 0, canvas, "dstAtop");
        draw1(dstInMode, 200, 0, canvas, "dstIn");
        draw1(dstOutMode, 200, 0, canvas, "dstOut");
        draw1(dstOverMode, 200, 0, canvas, "dstOver");
        draw1(screenMode, -800, 200, canvas, "screenMode");
        draw1(xorMode, 200, 0, canvas, "XOR");


        draw2(addMode,-200,200,canvas,"Add 2");
        draw2(darkenMode,200,0,canvas,"darken 2");
        draw2(lightenMode,200,0,canvas,"light 2");
        draw2(multiplyMode,200,0,canvas,"multiply 2");
        draw2(overlyMode,200,0,canvas,"overlay 2");

        draw2(srcMode, -800, 200, canvas, "Src 2");
        draw2(srcAtopMode, 200, 0, canvas, "srcAtop 2");
        draw2(srcInMode, 200, 0, canvas, "srcIn 2");
        draw2(srcOutMode, 200, 0, canvas, "srcOut 2");
        draw2(srcOverMode, 200, 0, canvas, "srcOver 2");

        draw2(dstMode, -800, 200, canvas, "dst 2");
        draw2(dstAtopMode, 200, 0, canvas, "dstAtop 2");
        draw2(dstInMode, 200, 0, canvas, "dstIn 2");
        draw2(dstOutMode, 200, 0, canvas, "dstOut 2");
        draw2(dstOverMode, 200, 0, canvas, "dstOver 2");
        draw2(screenMode, -800, 200, canvas, "screenMode 2");
        draw2(xorMode, 200, 0, canvas, "XOR 2");
    }

    private void draw2(PorterDuffXfermode mode, int dx, int dy, Canvas canvas, String text){
        canvas.translate(dx, dy);
        int layerID6 = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dst2Bitmap, 0, 0, mPaint2);
        mPaint2.setXfermode(mode);
        canvas.drawBitmap(src2Bitmap, 30,  30, mPaint2);
        mPaint2.setXfermode(null);
        canvas.drawText(text, 10, height * 2 - 50, mPaint2);
        canvas.restoreToCount(layerID6);
    }

    private void draw1(PorterDuffXfermode mode, int dx, int dy, Canvas canvas, String text) {
        canvas.translate(dx, dy);
        int layerID6 = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBitmap, 0, 0, mPaint2);
        mPaint2.setXfermode(mode);
        canvas.drawBitmap(srcBitmap, width >> 1, height >> 1, mPaint2);
        mPaint2.setXfermode(null);
        canvas.drawText(text, 10, height * 2 - 30, mPaint2);
        canvas.restoreToCount(layerID6);
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44); // 0xFFFFCC44   0xFFFF0000
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF); // 原 0xFF66AAFF  0xFF0000FF
        c.drawRect(0, 0, w, h, p);
        return bm;
    }

    static Bitmap makeDst2(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44); // 0xFFFFCC44   0xFFFF0000
//        c.drawOval(new RectF(0, 0, w, h), p);
        c.drawRect(0,0,w,h,p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc2(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF); // 原 0xFF66AAFF  0xFF0000FF
        c.drawRect(0, 0, w - 30, h - 30, p);
        return bm;
    }
}
