package com.softserveinc.ita.entity;

/**
 * Created by Andrey on 09.06.2014.
 */
public class Applicant {

    private String applicantID="";

    public Applicant(String applicantID){
        this.applicantID = applicantID;
    }

    public Applicant(){
    }

    public String getApplicantID() {
        return applicantID;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantID='" + applicantID + '\'' +
                '}';
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
}
