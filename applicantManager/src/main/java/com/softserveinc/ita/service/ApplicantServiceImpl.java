package com.softserveinc.ita.service;

import com.softserveinc.ita.DAO.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("applicantServiceImpl")
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    @Qualifier("applicantDAOImpl")
    private ApplicantDAO applicantDAO;

    @Override
    public List<String> getApplicantIDList() {
        List<Applicant> applicants = applicantDAO.getApplicants();
        if (applicants == null || applicants.isEmpty()) {
            return null;
        }
        List<String> applicantIDs = new ArrayList<>();
        for (Applicant applicant : applicants) {
            applicantIDs.add(applicant.getId());
        }
        return applicantIDs;
    }
}
