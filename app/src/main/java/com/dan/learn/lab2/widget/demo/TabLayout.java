package com.dan.learn.lab2.widget.demo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabLayout extends ViewGroup {

    private List<Rect> childrenRectArray = new ArrayList<>();
    private Rect rect;


    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //行高，行宽
    //父控件 高，父控件 宽
    //已使用宽度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();

        int widthUsed = 0;
        int lineWidthUsed = 0;
        int heightUsed = 0;
        int width;
        int height;
        int lineMaxHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child != null && child.getVisibility() != GONE) {
                if (childrenRectArray.size() <= i) {
                    rect = new Rect();
                    childrenRectArray.add(rect);
                } else {
                    rect = childrenRectArray.get(i);
                }

                int specWidth = MeasureSpec.getSize(widthMeasureSpec);
                int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
                //当 （子控件宽度 + 已使用宽度）> 父控件宽度时 换行
                if (specWidthMode != MeasureSpec.UNSPECIFIED && (lineWidthUsed + child.getMeasuredWidth()) > specWidth) {
                    lineWidthUsed = 0;
                    heightUsed += lineMaxHeight; //更新折行高度，重启一行
                    lineMaxHeight = 0;
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
                }
                MarginLayoutParams childParam = (MarginLayoutParams) child.getLayoutParams();
                int bM = childParam.bottomMargin;
                int lM = childParam.leftMargin;
                int tM = childParam.topMargin;
                int rM = childParam.rightMargin;

                int childMeasuredWidth = child.getMeasuredWidth();
                int childMeasuredHeight = child.getMeasuredHeight();
                rect.set(lM + lineWidthUsed, heightUsed + tM,
                        lineWidthUsed + childMeasuredWidth - rM , heightUsed + childMeasuredHeight - bM);

                lineWidthUsed += childMeasuredWidth;
                widthUsed = Math.max(widthUsed, lineWidthUsed);
                lineMaxHeight = Math.max(lineMaxHeight, childMeasuredHeight);
            }
        }

        width = widthUsed;
        height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child != null && child.getVisibility() != GONE) {
                Rect rect = childrenRectArray.get(i);
                child.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
