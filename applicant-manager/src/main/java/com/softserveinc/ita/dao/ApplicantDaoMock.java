package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("applicantDao")
public class ApplicantDaoMock implements ApplicantDao {

    public ApplicantDaoMock(){
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        List<Applicant> applicants = new ArrayList() {
            {
                add(new Applicant("id1"));
                add(new Applicant("id2"));
                add(new Applicant("id3"));
            }
        };
        for (Applicant applicant : applicants) {
            if (applicantId.equals(applicant.getApplicantID())) {
                return applicant;
            }
        }
        return null;
    }
}
