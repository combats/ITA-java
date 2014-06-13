package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;

import java.util.ArrayList;
import java.util.List;

public class ApplicantMockDao implements ApplicantDao {
    List<Applicant> applicants;

    @Override
    public List<Applicant> getApplicants() {
        applicants = new ArrayList<Applicant>();

        applicants.add(new Applicant("123"));
        applicants.add(new Applicant("124"));
        applicants.add(new Applicant("125"));

        return applicants;
    }
}
