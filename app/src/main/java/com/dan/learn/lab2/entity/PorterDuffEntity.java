package com.dan.learn.lab2.entity;

import android.graphics.PorterDuff;

public class PorterDuffEntity {
    public PorterDuffEntity(PorterDuff.Mode mode, String modeName) {
        this.mode = mode;
        this.modeName = modeName;
    }

    private PorterDuff.Mode mode;
    private String modeName;

    public PorterDuff.Mode getMode() {
        return mode;
    }

    public void setMode(PorterDuff.Mode mode) {
        this.mode = mode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
}
