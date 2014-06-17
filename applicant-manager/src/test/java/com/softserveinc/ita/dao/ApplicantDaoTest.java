package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ApplicantDaoTest extends BaseApplicantDaoTest {
    private List<Applicant> applicants = new ArrayList<>();

    @Autowired
    private ApplicantDao applicantDao;

    @Test
    public void testGetApplicantsListAndExpectedIsOk() {
        applicants.add(new Applicant("123"));
        applicants.add(new Applicant("124"));
        applicants.add(new Applicant("125"));

        assertEquals(applicants, applicantDao.getApplicants());
    }
}
