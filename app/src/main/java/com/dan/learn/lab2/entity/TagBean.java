package com.dan.learn.lab2.entity;

import java.io.Serializable;

/**
 * Created by: dan
 * Created time: 2020/12/15
 * Description:
 * Modify time:
 */
public class TagBean implements Serializable {
    private int tagId;
    private String tagName;

    public TagBean(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
