package com.softserveinc.ita.entity;

public class Applicant {
    private String id = "";

    public Applicant(){}

    public Applicant(String id) {
        this.id = id;
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
