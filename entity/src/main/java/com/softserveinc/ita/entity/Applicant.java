package com.softserveinc.ita.entity;

import java.io.Serializable;
import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Applicant implements Serializable {

    @Expose
    private String applicantID;

    @Expose
    private String name;

    public Applicant() {}

    public Applicant(String applicantID) {
        this.applicantID = applicantID;
    }

    public Applicant(String applicantID, String name) {
        this.applicantID = applicantID;
        this.name = name;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
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

        if (applicantID != null ? !applicantID.equals(applicant.applicantID) : applicant.applicantID != null)
            return false;
        if (name != null ? !name.equals(applicant.name) : applicant.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applicantID != null ? applicantID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantID='" + applicantID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

