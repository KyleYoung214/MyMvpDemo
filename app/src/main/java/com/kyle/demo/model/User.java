package com.kyle.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;

    private static final int[] types = new int[]{STUDENT, TEACHER};

    private String name;
    private int age;
    private String desc;
    private int type;

    public static int getTypeSize() {
        return types.length;
    }

    public User() {

    }

    public User(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[name: " + name + ", age: " + age + ", type: " + type + ", desc: " + desc + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.desc);
        dest.writeInt(this.type);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.desc = in.readString();
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
