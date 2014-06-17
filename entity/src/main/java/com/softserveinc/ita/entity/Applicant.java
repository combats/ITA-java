package com.softserveinc.ita.entity;

import java.io.Serializable;

public class Applicant implements Serializable{
    private String applicantID;

    public Applicant() {}

    public Applicant(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
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
