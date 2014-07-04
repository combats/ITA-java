package com.softserveinc.ita.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;


public class Applicant implements Serializable {

    @Expose
    private String id;
    @Expose
    private String name;
    private String surname;
  //  private int age;
    private String email;

    

    public Applicant() {}

    public Applicant(String applicantID) {
        this.id = applicantID;
    }

    public Applicant(String applicantID, String name) {
        this.id = applicantID;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getApplicantID() {
        return id;
    }

    public void setId(String applicantID) {
        this.id = applicantID;
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
        if (!(o instanceof Applicant)) return false;

        Applicant applicant = (Applicant) o;

        if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
        if (id != null ? !id.equals(applicant.id) : applicant.id != null) return false;
        if (name != null ? !name.equals(applicant.name) : applicant.name != null) return false;
        if (surname != null ? !surname.equals(applicant.surname) : applicant.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
