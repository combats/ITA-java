package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.exception.GroupNotFoundException;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    private ApplicantDAO applicantDao;

    @Override
    public List<Applicant> getApplicants() {
        return applicantDao.getApplicants();
    }

    public List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException {
        return applicantDao.getApplicantsInAGroup(groupID);
    }

    public Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException {
        Applicant searchedApplicant = applicantDao.getApplicantById(applicantId);
        if(searchedApplicant==null){
            throw new ApplicantDoesNotExistException();
        }
        return searchedApplicant;
    }

    public Applicant addNewApplicant(Applicant applicant){
        return applicantDao.addNewApplicant(applicant);
    }

    public Applicant editApplicant(Applicant applicant){
        return applicantDao.editApplicant(applicant);
    }
}
