package com.dan.learn.lab2.widget.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.utils.SizeUtil;

public class CanvasXorModeView extends View {

    private Bitmap destBitmap;
    private Bitmap srcBitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private PorterDuffXfermode xorMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Bitmap bitmap;
    private int srcWidth;
    private int srcHeight;
    private Bitmap bitmap2;
    private Bitmap bitmap21;

    public CanvasXorModeView(Context context) {
        super(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CanvasXorModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        int height = (int) SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_HEIGHT);
        int width = (int) SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_WIDTH);

        Configuration configuration = getResources().getConfiguration();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        srcWidth = (int) SizeUtil.dp2px(300);
        srcHeight = (int) SizeUtil.dp2px(200);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_identity_frame, options);

        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        float scaleW = srcWidth / bitmapWidth;
        float scaleH = srcHeight / bitmapHeight;

        System.out.println("原图宽高 -------------- ： " + srcWidth + " height:" + srcHeight + " W比值 ：" + scaleW + " H比值：" + scaleH + " 屏幕宽：" + width + " 高：" + height);


        Matrix matrix = new Matrix();
        matrix.setScale(scaleW, scaleH);

        bitmap21 = Bitmap.createBitmap(this.bitmap, 0, 0, (int) bitmapWidth, (int) bitmapHeight, matrix, true);

        System.out.println("原图宽高 -------------- ： " + bitmap21.getWidth() + " height:" + bitmap21.getHeight() + " 屏幕方向：" + configuration.orientation);
        int requestedOrientation = ((Activity) context).getRequestedOrientation();
        System.out.println("屏幕信息 -------------- ： " + configuration.orientation + " " + requestedOrientation);

        destBitmap = makeDst2(height, width);
        srcBitmap = makeSrc2(srcWidth, srcHeight);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerCount = canvas.saveLayer(0, 0, getWidth(), canvas.getHeight(), paint);
        canvas.drawBitmap(destBitmap, 0, 0, paint);
        paint.setXfermode(xorMode);
        canvas.drawBitmap(srcBitmap, (getWidth() >> 1) - (srcWidth >> 1),
                (getHeight() >> 2) - (srcHeight >> 1), paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerCount);
        canvas.drawBitmap(bitmap21, (getWidth() >> 1) - (srcWidth >> 1),
                (getHeight() >> 2) - (srcHeight >> 1), paint);
    }

    static Bitmap makeDst2(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0x1a000000); // 0xFFFFCC44   0xFFFF0000
        c.drawRect(0, 0, w, h, p);
        return bm;
    }

    Bitmap makeSrc2(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF); // 原 0xFF66AAFF  0xFF0000FF
        c.drawRect(0, 0, w , h , p);
        return bm;
    }
}
