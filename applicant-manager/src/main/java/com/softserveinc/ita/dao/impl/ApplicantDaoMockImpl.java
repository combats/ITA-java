package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicantDaoMockImpl implements ApplicantDao {

    public ApplicantDaoMockImpl(){
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
