package com.dan.learn.lab2.test;

/**
 * Created by: dan
 * Created time: 2020/8/4
 * Description:
 * Modify time:
 */
public class TestProguardClass {

    private String name;
    private int grade;
    public String className;
    public String classLocation;
    protected String classType;
    protected double classLength;

    public class ProguardSubClass {

        public ProguardSubClass(String sub_name) {
            this.sub_name = sub_name;
        }

        public ProguardSubClass(String sub_name, String sub_class) {
            this.sub_name = sub_name;
            this.sub_class = sub_class;
        }

        private String sub_name;
        public String sub_class;

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public String getSub_class() {
            return sub_class;
        }

        public void setSub_class(String sub_class) {
            this.sub_class = sub_class;
        }
    }

    protected class ProguardSub2Class {

        private ProguardSub2Class() {
        }

        public ProguardSub2Class(String sub2_name, String sub2_type) {
            this.sub2_name = sub2_name;
            this.sub2_type = sub2_type;
        }

        public ProguardSub2Class(String sub2_name) {
            this.sub2_name = sub2_name;
        }

        private String sub2_name;
        public String sub2_type;

        public String getSub2_name() {
            return sub2_name;
        }

        public void setSub2_name(String sub2_name) {
            this.sub2_name = sub2_name;
        }

        public String getSub2_type() {
            return sub2_type;
        }

        public void setSub2_type(String sub2_type) {
            this.sub2_type = sub2_type;
        }
    }

    private class ProguardClass {

        public ProguardClass(String test) {
            this.test = test;
        }

        String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public void setClassLocation(String classLocation) {
        this.classLocation = classLocation;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public double getClassLength() {
        return classLength;
    }

    public void setClassLength(double classLength) {
        this.classLength = classLength;
    }

    @Override
    public String toString() {
        return "TestProguardClass{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", className='" + className + '\'' +
                ", classLocation='" + classLocation + '\'' +
                ", classType='" + classType + '\'' +
                ", classLength=" + classLength +
                '}';
    }
}
