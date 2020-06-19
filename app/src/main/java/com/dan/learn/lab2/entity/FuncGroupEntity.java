package com.dan.learn.lab2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/10
 * Description:
 * Modify time:
 */
public class FuncGroupEntity {

    public FuncGroupEntity(String title) {
        this.title = title;
    }

    private String title;
    private List<FuncEntity> children;

    public String getTitle() {
        return title;
    }


    public List<FuncEntity> getChildren() {
        return children;
    }

    public void addChild(String name, String desc, Class target) {
        if (children == null) {
            children = new ArrayList<>();
        }
        FuncEntity child = new FuncEntity(name, desc, target);
        children.add(child);
    }

    public void clear() {
        if (children != null) {
            children.clear();
        }
    }
}
