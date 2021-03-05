package com.dan.learn.lab2.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by: dan
 * Created time: 2020/8/6
 * Description:
 * Modify time:
 */
public class Student  implements Parcelable {

    private String name;
    private int age;
    private String grade;
    private int dorm;

    public Student(){};
    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
        grade = in.readString();
        dorm = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getDorm() {
        return dorm;
    }

    public void setDorm(int dorm) {
        this.dorm = dorm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(grade);
        dest.writeInt(dorm);
    }
}
