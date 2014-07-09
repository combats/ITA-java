package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    private ApplicantDAO applicantDao;

    @Override
    public List<Applicant> getApplicants() {
        return applicantDao.getApplicants();
    }

    public List<Applicant> getApplicantsByGroupID(String groupID) {
        return applicantDao.getApplicantsByGroupID(groupID);
    }

    public Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException {
        Applicant searchedApplicant = applicantDao.getApplicantById(applicantId);
        if (searchedApplicant == null) {
            throw new ApplicantDoesNotExistException();
        }
        return searchedApplicant;
    }

    public Applicant addNewApplicant(Applicant applicant) {
        return applicantDao.addNewApplicant(applicant);
    }

    public Applicant editApplicant(Applicant applicant) {
        return applicantDao.editApplicant(applicant);
    }

    @Override
    public List<String> getApplicantIDList() {
        List<Applicant> applicants = applicantDao.getApplicants();
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
