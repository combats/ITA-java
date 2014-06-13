package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("applicantService")
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    @Qualifier("applicantDao")
    private ApplicantDao applicantDao;

    public Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException {
        Applicant searchedApplicant = applicantDao.getApplicantById(applicantId);
        if(searchedApplicant==null){
            throw new ApplicantDoesNotExistException();
        }
        return searchedApplicant;
    }
}
