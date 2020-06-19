package com.dan.learn.lab2.entity;

/**
 * Created by: dan
 * Created time: 2020/6/10
 * Description:
 * Modify time:
 */
public class FuncEntity {
    private String name;
    private String desc;
    private Class target;

    FuncEntity(String name, String desc, Class target) {
        this.name = name;
        this.desc = desc;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Class getTarget() {
        return target;
    }
}
