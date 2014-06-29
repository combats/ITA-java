<<<<<<< HEAD
package com.softserveinc.ita.entity;

import java.io.Serializable;


public class Applicant implements Serializable {
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
import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */

public class Applicant {

    @Expose
    private String id;

    @Expose
    private String name;

    public Applicant(){}

    public Applicant(String id) {
        this.id = id;
    }

    public Applicant(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;

        Applicant applicant = (Applicant) o;

        if (id != null ? !id.equals(applicant.id) : applicant.id != null) return false;

        return true;
    }
}
