package com.softserveinc.ita.entity;

import java.io.IOException;
import java.io.Serializable;

public class Applicant implements Serializable {
    private String ID = "";
    private String name = "";
    private String lastName = "";
    private String phone = "";
    private String email = "";
    private long birthday = 0;

    public enum Status {
        NOT_SCHEDULED, SCHEDULED, PASSED, NOT_PASSED, EMPLOYED;
    }

    ;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * REMOVE?
     *
     * @param ID
     * @param name
     * @param lastName
     * @param phone
     * @param email
     * @param birthday
     */
    public Applicant(String ID, String name, String lastName, String phone, String email, long birthday) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (birthday != applicant.birthday) return false;
        if (!ID.equals(applicant.ID)) return false;
        if (!email.equals(applicant.email)) return false;
        if (!lastName.equals(applicant.lastName)) return false;
        if (!name.equals(applicant.name)) return false;
        if (!phone.equals(applicant.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int) (birthday ^ (birthday >>> 32));
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public Applicant() {
    }
}
