package com.dan.learn.lab2.entity;

import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/10
 * Description:
 * Modify time:
 */
public class FuncGroupEntity {

    private String title;
    private List<FuncEntity> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FuncEntity> getChildren() {
        return children;
    }

    public void setChildren(List<FuncEntity> children) {
        this.children = children;
    }
}
