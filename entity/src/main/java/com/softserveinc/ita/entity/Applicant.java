package com.softserveinc.ita.entity;

import java.io.Serializable;


public class Applicant implements Serializable {

    private static final String NOT_SCHEDULED_TEMPLATE_REF ="";
    private static final String PASSED_TEMPLATE_REF ="";
    private static final String NOT_PASSED_TEMPLATE_REF ="";
    private String applicantID;
    private String name;
    private String surname;
    private String email;
    private Status status;

    enum Status {
        NOT_SCHEDULED(NOT_SCHEDULED_TEMPLATE_REF), PASSED(PASSED_TEMPLATE_REF), NOT_PASSED(NOT_PASSED_TEMPLATE_REF);

        private String templateRef;

        Status(String templateRef){
            this.templateRef=templateRef;
        }

        public String getTemplateRef() {
            return templateRef;
        }

        public void setTemplateRef(String templateRef) {
            this.templateRef = templateRef;
        }
    };

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
