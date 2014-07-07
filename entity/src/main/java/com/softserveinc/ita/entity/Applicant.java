package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Applicants")
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NOT_SCHEDULED_TEMPLATE_REF ="mailTemplaits/interviewInvitation.vm";
    private static final String PASSED_TEMPLATE_REF ="mailTemplaits/interviewPassed.vm";
    private static final String NOT_PASSED_TEMPLATE_REF ="mailTemplaits/interviewFailed";
    private static final String NOT_SCHEDULED_SUBJECT = "Interview invitation";
    private static final String PASSED_SUBJECT = "Interview passed";
    private static final String NOT_PASSED_SUBJECT = "Interview failed";

    public enum Status {
        NOT_SCHEDULED(NOT_SCHEDULED_TEMPLATE_REF, NOT_SCHEDULED_SUBJECT),
        PASSED(PASSED_TEMPLATE_REF, PASSED_SUBJECT),
        NOT_PASSED(NOT_PASSED_TEMPLATE_REF, NOT_PASSED_SUBJECT);

        private String templateRef;
        private String subject;

        Status(String templateRef, String subject) {
            this.templateRef=templateRef;
            this.subject=subject;
        }

        public String getTemplateRef() {
            return templateRef;
        }

        public void setTemplateRef(String templateRef) {
            this.templateRef = templateRef;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    };

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String id;

    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Email")
    private String email;
    @Column(name = "GroupId")
    private String groupId;
    private Status status;

    public Applicant() {}

    public Applicant(String applicantId) {
        this.name = applicantId;
    }

    public Applicant(String name, String groupId) {
        this.name = name;
        this.groupId = groupId;
    }

    public Applicant (String id, String name, String surname, Status status){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String applicantId) {
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;

        Applicant applicant = (Applicant) o;

        if (id != null ? !id.equals(applicant.id) : applicant.id != null)
            return false;
        if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
        if (groupId != null ? !groupId.equals(applicant.groupId) : applicant.groupId != null) return false;
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
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
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
                ", groupId='" + groupId + '\'' +
                '}';
    }
}