package com.dan.learn.lab2.entity;

/**
 * Created by: dan
 * Created time: 2020/7/7
 * Description:
 * Modify time:
 */
public class Item {

    public Item(String title, int index) {
        this.title = title;
        this.index = index;
    }

    private String title;
    private int index;
    private boolean checked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
