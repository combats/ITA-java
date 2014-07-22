package com.softserveinc.ita.entity;

public class User {

    private String ID = "";
    private String name = "";
    private String lastName = "";

    public User() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = lastName;
    }

    public User(String ID, String lastName, String name) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
    }

    public User(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!ID.equals(user.ID)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

