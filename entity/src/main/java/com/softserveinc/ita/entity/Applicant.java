package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import com.google.gson.annotations.Expose;

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
    @Expose
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Email")
    private String email;
    @Column(name = "GroupId")
    private String groupId;

    public Applicant() {}

    public Applicant(String applicantId) {
        this.name = applicantId;
    }
    public Applicant(String name, String groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

       // if (age != applicant.age) return false;
        if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
        if (id != null ? !id.equals(applicant.id) : applicant.id != null) return false;
        if (name != null ? !name.equals(applicant.name) : applicant.name != null) return false;
        if (surname != null ? !surname.equals(applicant.surname) : applicant.surname != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
               // ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
      //  result = 31 * result + age;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
