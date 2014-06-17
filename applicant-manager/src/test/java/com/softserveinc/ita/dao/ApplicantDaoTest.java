package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ApplicantDaoTest extends BaseApplicantDaoTest{
    List<Applicant> applicantList;

    @Autowired
    private ApplicantDao applicantDao;

    @Test
    public void testGetApplicantsInGroupAndExpectedIsOk() throws GroupNotFoundException {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("123"));
        applicants.add(new Applicant("124"));
        applicants.add(new Applicant("125"));

        assertEquals(applicants, applicantDao.getApplicantsInAGroup("1"));
    }

    @Test(expected = GroupNotFoundException.class)
    public void testGetApplicantsInGroupAndExceptionExpected() throws GroupNotFoundException {
        applicantList = applicantDao.getApplicantsInAGroup("2");
    }
}
