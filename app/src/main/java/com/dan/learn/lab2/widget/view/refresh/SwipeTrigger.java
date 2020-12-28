package com.dan.learn.lab2.widget.view.refresh;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public interface SwipeTrigger {
    void onPrepare();

    void onMove(int y, boolean isComplete, boolean automatic);

    void onRelease();

    void onComplete();

    void onReset();
}
