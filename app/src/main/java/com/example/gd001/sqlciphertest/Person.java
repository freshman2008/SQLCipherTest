package com.example.gd001.sqlciphertest;

/**
 * Created by gd001 on 17-8-1.
 */

public class Person {
    private String name;
    private int age;
    private String sex;
    private int _id;

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
