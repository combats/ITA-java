package com.softserveinc.ita.DAO.impl;

import com.softserveinc.ita.DAO.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("applicantDAOImpl")
public class ApplicantDAOMockImpl implements ApplicantDAO {
    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("id1"));
        applicants.add(new Applicant("id2"));
        applicants.add(new Applicant("idX"));
        return applicants;
    }
}
