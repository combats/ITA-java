package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
    @Expose
    private String id;

    @Column(name = "Name")
    private String name = DEFAULT_USER_NAME;

    @Column(name = "Surname")
    @Expose
    private String surname;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Age")
    private int age = DEFAULT_USER_AGE;

    @Column(name = "Password")
    private String password;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="UserRoles",
            joinColumns = {@JoinColumn(name="UserId", referencedColumnName="Id")},
            inverseJoinColumns = {@JoinColumn(name="RoleId", referencedColumnName="Id")}
    )
    private Role role;
    @Column(name = "Active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserQuestions", joinColumns = {
            @JoinColumn(name = "UserId", referencedColumnName = "Id") }, inverseJoinColumns = {
            @JoinColumn(name = "QuestionId", referencedColumnName = "Id") })

    private List<Question> questions;

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

    public User(String name, String surname, String phone, String email, int age, String password, Set<Role> securityRoleCollection, boolean active, List<Question> questions) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.password = password;
        this.active = active;
        this.questions = questions;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (active != user.active) return false;
        if (age != user.age) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (questions != null ? !questions.equals(user.questions) : user.questions != null) return false;
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
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
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
                ", password='" + password + '\'' +
                ", active=" + active +
                ", questions=" + questions +
                '}';
    }
}