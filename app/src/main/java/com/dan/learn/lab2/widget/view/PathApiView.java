package com.dan.learn.lab2.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Path API
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PathApiView extends View {

    private Path path1, path2, path3, path4, path5, path6, path7, path8, path9, path10,path11;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PathApiView(Context context) {
        super(context);
    }

    public PathApiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        path1 = new Path();
        path1.addCircle(100, 100, 50, Path.Direction.CW);
        path2 = new Path();
        path2.moveTo(50, 170);
        path2.lineTo(200, 170);
        path2.lineTo(200, 230);
        path2.rLineTo(100, 0); //以上一个节点或原点为始发点，绘制相对dx,dy的距离

        path2.rMoveTo(50, 50); //以上一个节点或原点为始发点，移动相对dx,dy的距离
        path2.rLineTo(50, 0);

        RectF rect = new RectF(50.5f, 200.3f, 200.7f, 400.0f);
        path3 = new Path();
        path3.arcTo(rect, 270, 350, false);

        RectF emptyRectF = new RectF();
        path3.computeBounds(emptyRectF, true); //测量path的rect,然后将参数赋值给新的rect，exact参数 不再使用
        Log.d("打印 computeBounds ", "--------------emptyRectF l: "
                + emptyRectF.left + " r:" + emptyRectF.right + " t:" + emptyRectF.top + " b:" + emptyRectF.bottom);

//        path3.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        boolean isConvex = path3.isConvex(); //是否是凸面
        boolean isEmpty = path3.isEmpty(); //是否不包含 line，curves 曲线
        boolean isInverseFillType = path3.isInverseFillType(); //填充类型是否是inverse中的一种
        boolean isRect = path3.isRect(emptyRectF); //是否是指定的rect

        Log.d("打印 ----------- ", "isConvex :" + isConvex + " isEmpty:" + isEmpty + " isInverseFillType：" + isInverseFillType + " isRect：" + isRect);

        path4 = new Path();
        path4.moveTo(350, 100);
        path4.offset(20, 20);
        path4.addCircle(400, 300, 100, Path.Direction.CW);

        path5 = new Path();
        path5.addCircle(500, 330, 100, Path.Direction.CW);
//        path4.op(path5, Path.Op.DIFFERENCE); //从第一条路径中减去第二条路径
        path4.op(path5, Path.Op.INTERSECT); //保留两者相交部分路径
//        path4.op(path5, Path.Op.REVERSE_DIFFERENCE); //从第二条路径中减去第一条路径
//        path4.op(path5, Path.Op.UNION); // 保留两条路径非相交部分
//        path4.op(path5, Path.Op.XOR); // 保留两条路径

        path6 = new Path();
        path6.addCircle(400, 300, 100, Path.Direction.CW);
        path7 = new Path();
        path7.addCircle(500, 330, 100, Path.Direction.CW);

        path8 = new Path();
        path9 = new Path();
//        path7.cubicTo();
        path8.moveTo(300, 500);
        //x1,y1是控制点 x2，y2是终点坐标，起点是moveTo()指定的坐标，或者0,0
        path8.quadTo(100, 600, 900, 600);

        path9.moveTo(600, 600);
        path9.cubicTo(200, 900, 600, 800, 1000, 1000);

        path10 = new Path();
        path10.moveTo(260, 800);
        path10.lineTo(400, 1000);
        path10.lineTo(600, 1000);
        path10.lineTo(800, 1200);

        path11 = new Path();
        path11.moveTo(330,900);
        path11.quadTo(400, 1000, 500, 1000);
        path11.quadTo(600, 1000, 700, 1100);
        //连续调用quaTo时，前一个终点将作为新的起点。为了使线段平滑，要使倒数第二个触点作为新的控制点
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawPath(path1, mPaint);

        canvas.drawPath(path2, mPaint);

        canvas.drawPath(path3, mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);

        canvas.drawPath(path6, mPaint);
        canvas.drawPath(path7, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);

        canvas.drawPath(path4, mPaint);

        canvas.drawPath(path8, mPaint);

        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        //沿着path绘制文字， hOffset 文字距离开始位置的偏移量  vOffset 文字距path上下偏移量
        canvas.drawTextOnPath(" 二 阶 贝 塞 尔 曲 线 ", path8, 120, 30, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawPath(path9, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath("三阶贝塞尔曲线", path9, 150, 50, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(path10, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawPath(path11,mPaint);
    }


}
