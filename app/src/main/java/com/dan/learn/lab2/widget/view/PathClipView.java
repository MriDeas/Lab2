package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dan.learn.lab2.R;

/**
 * Created by: dan
 * Created time: 2020/7/29
 * Description:
 * Modify time:
 */
public class PathClipView extends androidx.appcompat.widget.AppCompatImageView {

    private float[] radiusArr = new float[8];
    private Path path;
    private RectF rectF = new RectF();
    private GradientDrawable gradientDrawable;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PathClipView(Context context) {
        super(context);
    }

    public PathClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Drawable drawable = getResources().getDrawable(R.drawable.bac_solid_red);
        if (drawable instanceof ShapeDrawable) {
            Log.d("PathClipView", "shape ------------ ");
        }

    }

    {
        radiusArr[0] = 150;
        radiusArr[1] = 150;
        radiusArr[2] = 150;
        radiusArr[3] = 150;
        radiusArr[4] = 0;
        radiusArr[5] = 0;
        radiusArr[6] = 50;
        radiusArr[7] = 50;
        path = new Path();

        gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(radiusArr);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(0, 0, w, h);
        path.addRoundRect(rectF, radiusArr, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);


    }
}
