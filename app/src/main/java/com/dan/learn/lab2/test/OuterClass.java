package com.dan.learn.lab2.test;

/**
 * Created by: dan
 * Created time: 2021/3/3
 * Description:
 * Modify time:
 */
public class OuterClass {

    private String name;
    protected int count;
    public float rate;

    public class InnerClass {
        private String innerName;

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

        public OuterClass getOuter() {
            return OuterClass.this;
        }
    }

    public static class InnerStaticClass {
        private String staticName;

        public String getStaticName() {
            return staticName;
        }

        public void setStaticName(String staticName) {
            this.staticName = staticName;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
