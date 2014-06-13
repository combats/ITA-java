package com.softserveinc.ita.entity;

/**
 * Created by Anton on 04.06.2014.
 */
public class User {
    private String userID;

    public User() {
    }

    public User(String userID) {

        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return userID != null ? userID.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userID != null ? !userID.equals(user.userID) : user.userID != null) return false;

        return true;
    }
}