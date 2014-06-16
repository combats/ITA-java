package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDao applicantDao;

    public Applicant addNewApplicant(Applicant applicant){
        return applicantDao.addNewApplicant(applicant);
    }

    public Applicant getApplicantById(String applicantId){
        return applicantDao.getApplicantById(applicantId);
    }
}
