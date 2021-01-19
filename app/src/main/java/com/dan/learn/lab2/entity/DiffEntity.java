package com.dan.learn.lab2.entity;

/**
 * Created by: dan
 * Created time: 2021/1/18
 * Description:
 * Modify time:
 */
public class DiffEntity {

    public static final String ENTITY_DESC = "desc_data";
    public static final String ENTITY_STATE = "state_data";
    public static final String ENTITY_TITLE = "title_data";

    private String title;
    private String desc;
    private String state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
