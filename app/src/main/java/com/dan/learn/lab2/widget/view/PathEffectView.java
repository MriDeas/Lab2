package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathEffectView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path1, stampPath;

    private PathEffect mPathDashPathEffect, mPathDashPathEffect2, mPathDashPathEffect3;
    private PathEffect mCornerPathEffect;
    private PathEffect mDashPathEffect;
    private PathEffect mSumPathEffect;
    private PathEffect mDiscretePathEffect;
    private PathEffect mComposePathEffect;


    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        path1 = new Path();
        stampPath = new Path();

        path1.moveTo(100, 100);
        path1.lineTo(300, 200);
        path1.lineTo(400, 50);
        path1.lineTo(500, 150);
        path1.lineTo(700, 100);

        stampPath.addCircle(0, 0, 5, Path.Direction.CCW);

        stampPath.moveTo(0, 20);
        stampPath.lineTo(10, 0);
        stampPath.lineTo(20, 20);
        stampPath.close();
        stampPath.addCircle(0, 0, 3, Path.Direction.CCW);

        // 给要绘制的path 添加以其他path成型的图案, style 参数用来描绘转角部分图案样式
        mPathDashPathEffect = new PathDashPathEffect(stampPath, 10, 20, PathDashPathEffect.Style.TRANSLATE);
        mPathDashPathEffect2 = new PathDashPathEffect(stampPath, 10, 20, PathDashPathEffect.Style.ROTATE);
        mPathDashPathEffect3 = new PathDashPathEffect(stampPath, 10, 20, PathDashPathEffect.Style.MORPH);

        //处理拐角处的圆角，radius 表示圆角半径
        mCornerPathEffect = new CornerPathEffect(40);

        float[] intervals = new float[]{15, 5, 30, 5, 5};
        //intervals 元素数量必须大于2，且是2的倍数才能生效，大于2且不是2的倍数，则最后一个数值不生效
        //phase 开始绘制的偏移量
        mDashPathEffect = new DashPathEffect(intervals, 3);

        //将两个效果叠加在一起，不区分先后顺序
        mSumPathEffect = new SumPathEffect(mCornerPathEffect, mDashPathEffect);

        //离散的线段，segmentLength 是单个线段的长度，deviation 是从原路径随机偏移的距离最大值
        mDiscretePathEffect = new DiscretePathEffect(10, 30);

        //它会先将第二个参数的PathEffect innerpe 的特效作用于路径上，然后再在此加了特效的路径上作用第二个特效
        mComposePathEffect = new ComposePathEffect(mDashPathEffect, mCornerPathEffect);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(null);
        canvas.drawPath(path1, mPaint);
        drawText("原图线", 750, 100, canvas);

        canvas.translate(0, 100);
        mPaint.setColor(Color.RED);
        mPaint.setPathEffect(mPathDashPathEffect);
        canvas.drawPath(path1, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        drawText("PathDashPathEffect TRANSLATE 风格", 750, 100, canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(0, 100);
        mPaint.setPathEffect(mPathDashPathEffect2);
        canvas.drawPath(path1, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        drawText("PathDashPathEffect ROTATE 风格", 750, 100, canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(0, 100);
        mPaint.setPathEffect(mPathDashPathEffect3);
        canvas.drawPath(path1, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        drawText("PathDashPathEffect MORPH 风格", 750, 100, canvas);
//        canvas.drawText("PathDashPathEffect MORPH 风格", 750, 100, mPaint);

        canvas.translate(0, 200);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(mCornerPathEffect);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(path1, mPaint);

        canvas.translate(0, 100);
        mPaint.setPathEffect(mDashPathEffect);
        mPaint.setColor(Color.CYAN);
        canvas.drawPath(path1, mPaint);

        canvas.translate(0, 100);
        mPaint.setPathEffect(mSumPathEffect);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawPath(path1, mPaint);

        canvas.translate(0, 100);
        mPaint.setPathEffect(mDiscretePathEffect);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.translate(0, 100);
        mPaint.setPathEffect(mComposePathEffect);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path1, mPaint);

    }

    private void drawText(String text, float x, float y, Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(null);
        canvas.drawText(text, x, y, mPaint);
    }
}
