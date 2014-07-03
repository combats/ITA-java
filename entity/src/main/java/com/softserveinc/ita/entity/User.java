package com.softserveinc.ita.entity;

import java.util.Set;

public class User {

    public static final int DEFAULT_USER_AGE = 0;
    public static final String DEFAULT_USER_NAME = "";
    public static final String DEFAULT_USER_ID = "";
    private String id = DEFAULT_USER_ID;
    private String name = DEFAULT_USER_NAME;
    private String surname;
    private String phone;
    private String email;
    private int age = DEFAULT_USER_AGE;
    private String password;
    private Set securityRoleCollection;
    private boolean active;
    private Role role;

    public User() {
    }

    public User(String userID) {
        this.id = userID;
    }

    public User(String userID, String name, int age) {
        this.id = userID;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String userID) {
        this.id = userID;
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

    public boolean getActive() {return active;}

    public void setActive(boolean active) {this.active = active;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getLogin() { return email;}

    public void setLogin(String login) {this.email = login;}

    public Set<Role> getSecurityRoleCollection() {return securityRoleCollection;}

    public void setSecurityRoleCollection(Set securityRoleCollection) {
        this.securityRoleCollection = securityRoleCollection;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}

