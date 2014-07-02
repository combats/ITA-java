
package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */

public class User {

    private List<Question> Questions = new ArrayList<>();

    public static final int DEFAULT_USER_AGE = 0;
    public static final String DEFAULT_USER_NAME = "";
    public static final String DEFAULT_USER_ID = "";
    @Expose
    private String userID = DEFAULT_USER_ID;
    @Expose
    private String name = DEFAULT_USER_NAME;
    private int age = DEFAULT_USER_AGE;

    public User() {
    }

    public User(String userID) {
        this.userID = userID;
    }

    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public User(String userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestion(List<Question> questions) {
        Questions = questions;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", Questions=" + Questions +
                ", userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (Questions != null ? !Questions.equals(user.Questions) : user.Questions != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (userID != null ? !userID.equals(user.userID) : user.userID != null) return false;
        if (userID != null ? !userID.equals(user.userID) : user.userID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (Questions != null ? Questions.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
