package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Applicants")
@Indexed
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    @Expose
    private String id;

    @Column(name = "Name")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String name;
    @Column(name = "Surname")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String surname;
    @Column(name = "Email")
    private String email;

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

        if (id != null ? !id.equals(applicant.id) : applicant.id != null)
            return false;
        if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
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
//                "version=" + version +
                ", applicantId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
