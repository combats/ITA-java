package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Applicants")
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NOT_SCHEDULED_TEMPLATE_REF = "mailTemplaits/notScheduledLetter.vm";
    private static final String SCHEDULED_TEMPLATE_REF = "mailTemplaits/scheduledLetter.vm";
    private static final String PASSED_TEMPLATE_REF = "mailTemplaits/passedLetter.vm";
    private static final String NOT_PASSED_TEMPLATE_REF = "mailTemplaits/notPassedLetter.vm";
    private static final String EMPLOYED_TEMPLATE_REF = "mailTemplaits/employedLetter.vm";
    private static final String NOT_SCHEDULED_SUBJECT = "Application accepted";
    private static final String SCHEDULED_SUBJECT = "Interview invitation";
    private static final String PASSED_SUBJECT = "Interview passed";
    private static final String NOT_PASSED_SUBJECT = "Interview failed";
    private static final String EMPLOYED_SUBJECT = "Employed";

    public enum Status {
        NOT_SCHEDULED(NOT_SCHEDULED_TEMPLATE_REF, NOT_SCHEDULED_SUBJECT),
        SCHEDULED(SCHEDULED_TEMPLATE_REF, SCHEDULED_SUBJECT),
        PASSED(PASSED_TEMPLATE_REF, PASSED_SUBJECT),
        NOT_PASSED(NOT_PASSED_TEMPLATE_REF, NOT_PASSED_SUBJECT),
        EMPLOYED(EMPLOYED_TEMPLATE_REF, EMPLOYED_SUBJECT);

        private String templateRef;
        private String subject;

        Status(String templateRef, String subject) {
            this.templateRef = templateRef;
            this.subject = subject;
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
    }

    public Applicant(String name, String lastName, String email, String phone, long birthday) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String id;

    @Column(name = "Name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Birthday")
    private long birthday;

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Applicant() {
    }

    public Applicant(String applicantId) {
        this.id = applicantId;
    }

    public Applicant(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (birthday != applicant.birthday) return false;
        if (!email.equals(applicant.email)) return false;
        if (!id.equals(applicant.id)) return false;
        if (!lastName.equals(applicant.lastName)) return false;
        if (!name.equals(applicant.name)) return false;
        if (!phone.equals(applicant.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (int) (birthday ^ (birthday >>> 32));
        return result;
    }

    public Applicant(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                '}';
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
