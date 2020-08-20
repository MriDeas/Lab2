package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by: dan
 * Created time: 2020/7/27
 * Description:
 * Modify time:
 */
public class PaintTextView extends View {

    private Paint paint;

    private Rect paintTextRect = new Rect();
    private int lastHeight = 0;
    private int heightSum = 30;
    private int textLineHeight = 0;
    private int alignSummaryY;

    public PaintTextView(Context context) {
        this(context, null);
    }

    public PaintTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reset();
        drawUnderLine(canvas);
        drawTypeFace(canvas);
        drawBoldAndSkew(canvas);
        drawTextWithLetterSpacing(canvas);
        drawElegantTextHeight(canvas);
        drawDeleteAndScaleText(canvas);
        drawTextWithFlag(canvas);
        textLineHeight = heightSum;
        Paint.Align textAlign = paint.getTextAlign();
        String alignName = textAlign != null ? textAlign.name() : "align null";

        canvas.drawText(alignName,100,textLineHeight + 100,paint);
        textLineHeight = heightSum + 300;
        drawTextAlign(canvas);
    }

    private void drawTextWithFlag(Canvas canvas) {
        reset();
        paint.setColor(Color.GRAY);
        int h = heightSum + 30;
        heightSum = h;
        canvas.drawLine(10, h, getWidth() - 10, h, paint);
        paint.setColor(Color.BLACK);
        String strike_thru_text = "strike thru text flag";
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        heightSum += 30;
        canvas.drawText("strike_thru_text", 10, heightSum, paint);
        measureTextHeight(strike_thru_text);
        String underlineText = "underline text flag";
        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        canvas.drawText(underlineText, 10, heightSum, paint);
        measureTextHeight(underlineText);

        String fakeBoldText = "fake bold text flag";
        paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        canvas.drawText(fakeBoldText, 10, heightSum, paint);
        measureTextHeight(fakeBoldText);


        paint.setFlags(Paint.LINEAR_TEXT_FLAG);
        paint.setFlags(Paint.SUBPIXEL_TEXT_FLAG);
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        paint.setFlags(Paint.DITHER_FLAG);

        String lastText = "last text f a b c d e f";
        canvas.drawText(lastText, 10, heightSum, paint);
        measureTextHeight(lastText);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            paint.setFlags(Paint.EMBEDDED_BITMAP_TEXT_FLAG);
        }
    }

    private void drawTextAlign(Canvas canvas) {
        reset();
        String summary = "align left在X坐标右边👉，align right在左👈，center在中间";
        alignSummaryY = textLineHeight + lastHeight * 9;
        canvas.drawText(summary, 10, alignSummaryY, paint);

        paint.setColor(Color.RED);
        canvas.drawLine(200, textLineHeight - 50, 200, textLineHeight + lastHeight * 5, paint);
        canvas.drawText("200", 200, textLineHeight - 50, paint);

        paint.setColor(Color.GRAY);
        String textLeft = "left align text";
        String textCenter = "center align text";
        String textRight = "right align text";
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(textLeft, 200, heightSum, paint);
        measureTextHeight(textLeft);

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(textCenter, 200, heightSum, paint);
        measureTextHeight(textCenter);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(textRight, 200, heightSum, paint);
        measureTextHeight(textRight);

        float center = paint.measureText(textCenter);
        float left = paint.measureText(textLeft);
        float right = paint.measureText(textRight);
        float max = Math.max(center, left);
        max = Math.max(right, max);

        paint.setColor(Color.BLACK);
        canvas.drawLine(200 + max, textLineHeight, 200 + max, textLineHeight + lastHeight * 5, paint);
        canvas.drawText(String.valueOf(200 + max), 200 + max, textLineHeight + lastHeight * 5, paint);
    }

    private void drawUnderLine(Canvas canvas) {
        reset();
        String text = "paint.setUnderlineText(true)";
        paint.setUnderlineText(true);
        paint.getTextBounds(text, 0, text.length(), paintTextRect);
        lastHeight = paintTextRect.height();
        canvas.drawText(text, 10, heightSum, paint);
        heightSum = lastHeight + 60;
    }

    private void drawTypeFace(Canvas canvas) {
        reset();
        String text = "paint.setTypeface(Typeface.SERIF)";
        paint.setTypeface(Typeface.SERIF); //字体
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawBoldAndSkew(Canvas canvas) {
        reset();
        paint.setTextSkewX(0.5f); //设置 文字偏斜  正值偏向左，负值 偏向右
        paint.setFakeBoldText(true); //加粗

        String text = "setTextSkewX & setFakeBoldText";
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawTextWithLetterSpacing(Canvas canvas) {
        reset();
        String text = "setLetterSpacing(0.05f)";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setLetterSpacing(0.05f);
        }
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }


    private void drawElegantTextHeight(Canvas canvas) {
        reset();
        String text = "setElegantTextHeight(true) ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setElegantTextHeight(true);
        }

        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void drawDeleteAndScaleText(Canvas canvas) {
        reset();
        String text = "删除线setStrikeThruText & setScaleX";
        paint.setStrikeThruText(true);
        paint.setTextScaleX(1.25f);
        canvas.drawText(text, 10, heightSum, paint);
        measureTextHeight(text);
    }

    private void measureTextHeight(String text) {
        paint.getTextBounds(text, 0, text.length(), paintTextRect);
        lastHeight = paintTextRect.height();
        heightSum += lastHeight + 30;
    }

    private void reset() {
        paint.setTextSize(25);
        paint.setTextSkewX(0);
        paint.setUnderlineText(false);
        paint.setFakeBoldText(false);
        paint.setStrikeThruText(false);
        paint.setTextAlign(Paint.Align.LEFT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setLetterSpacing(0);
        }
    }

}
