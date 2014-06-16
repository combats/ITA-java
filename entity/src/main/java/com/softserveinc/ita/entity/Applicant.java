package com.softserveinc.ita.entity;

public class Applicant {
    private String applicantID;

    public String getApplicantID() {
        return applicantID;
    }

    public Applicant() {
    }

    public Applicant(String id) {
        applicantID = id;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "ID=" + applicantID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (!applicantID.equals(applicant.applicantID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return applicantID.hashCode();
    }
}
