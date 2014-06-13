package com.softserveinc.ita.entity;

public class Applicant {
    public String getId() {
        return Id;
    }

    public Applicant() {
    }

    public Applicant(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "Id='" + Id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (!Id.equals(applicant.Id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    private String Id;
}
