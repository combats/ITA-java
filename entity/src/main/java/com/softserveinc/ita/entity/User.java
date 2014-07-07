package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_USER_AGE = 0;
    public static final String DEFAULT_USER_NAME = "";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String id;

    @Column(name = "Name")
    private String name = DEFAULT_USER_NAME;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Age")
    private int age = DEFAULT_USER_AGE;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "UserRoles",
    joinColumns = {@JoinColumn(name = "UserId", referencedColumnName = "Id")},
    inverseJoinColumns = {@JoinColumn(name = "RoleId", referencedColumnName = "Id")})
    private Role role;

    public User() {
    }

    public User(String userId) {
        this.id = userId;
    }
    public User(String userName, String userSurname) {
        this.name = userName;
        this.surname = userSurname;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
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
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}

