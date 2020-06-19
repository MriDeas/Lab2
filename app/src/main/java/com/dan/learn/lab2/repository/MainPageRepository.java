package com.dan.learn.lab2.repository;

import com.dan.learn.lab2.entity.FuncGroupEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dan
 * Created time: 2020/6/11
 * Description:
 * Modify time:
 */
public class MainPageRepository {

    private List<FuncGroupEntity> mGroup;

    private static class Holder {
        static MainPageRepository instance = new MainPageRepository();
    }

    public static MainPageRepository getInstance() {
        return Holder.instance;
    }

    public FuncGroupEntity createGroup(String title) {
        if (mGroup == null) {
            mGroup = new ArrayList<>();
        }
        FuncGroupEntity group = new FuncGroupEntity(title);
        mGroup.add(group);
        return group;
    }

    public List<FuncGroupEntity> getData() {
        return mGroup;
    }

    public void clear() {
        if (mGroup != null) {
            for (FuncGroupEntity e : mGroup) {
                e.clear();
            }
            mGroup.clear();
        }
    }

}
