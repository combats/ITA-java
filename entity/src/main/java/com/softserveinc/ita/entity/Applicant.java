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
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (applicantID != null ? !applicantID.equals(applicant.applicantID) : applicant.applicantID != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return applicantID != null ? applicantID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantID='" + applicantID + '\'' +
                '}';
    }
}
