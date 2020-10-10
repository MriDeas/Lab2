package com.dan.learn.lab2.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ScalableImageView extends View {

    //两种尺寸 小图 长边顶着View的边界 大图 图片的长短边都超出View的边界
    //双击 切换两种尺寸，伴随动画
    //大尺寸可以用手指滑动
    //滑动 松手时可以惯性滑动
    //滑动和惯性滑动 都不能超出范围

    //步骤：
    //1. 根据图片宽高和View的宽高 计算合适的两种尺寸比例
    //2. 实现触摸事件监听接口，支持双击，切换两种尺寸
    //3. 动画 创建属性动画，自定义View的属性，根据当前尺寸执行动画
    //4. 缩放的实现 使用canvas的API scale（）
    //5. 滑动 和边界判断 在onScroll中判断当前滑动位置 和边界范围，使用canvas.translate() 偏移位置 = 上一次偏移量 - 本次偏移量
    //   边界 ： 图片应该居中放置， 图片边界到View边界的距离是偏移量的范围  需要考虑缩放比例  

    public ScalableImageView(Context context) {
        super(context);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
