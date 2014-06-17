package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDao applicantDao;

    @Override
    public List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException {
        return applicantDao.getApplicantsInAGroup(groupID);
    }
}
