package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Applicants")
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    @Expose
    private String id;

    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Email")
    private String email;

    public enum Status {
        NOT_SCHEDULED("NOT_SCHEDULED"), SCHEDULED("SCHEDULED"), PASSED("PASSED"), NOT_PASSED("NOT_PASSED");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public Applicant() {}

    public Applicant(String applicantId) {
        this.id = applicantId;
    }
    public Applicant(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
