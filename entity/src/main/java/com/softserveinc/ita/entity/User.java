package com.softserveinc.ita.entity;

public class User {
    private String userID = "";

    public String getUserID() {
        return userID;
    }

    public User(String userID) {
        this.userID = userID;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userID.equals(user.userID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }
}
