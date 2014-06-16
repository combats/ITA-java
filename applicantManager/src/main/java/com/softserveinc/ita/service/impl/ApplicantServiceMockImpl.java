package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.DAO.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("applicantServiceImpl")
public class ApplicantServiceMockImpl implements ApplicantService {
    @Autowired
    private ApplicantDAO applicantDAOImpl;

    @Override
    public List<String> getApplicantIDList() {
        List<Applicant> applicants = applicantDAOImpl.getApplicants();
        if (applicants == null || applicants.isEmpty()) {
            return null;
        }
        List<String> applicantIDs = new ArrayList<>();
        for (Applicant applicant : applicants) {
            applicantIDs.add(applicant.getApplicantID());
        }
        return applicantIDs;
    }
}
