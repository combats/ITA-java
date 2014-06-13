package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static junit.framework.Assert.assertEquals;

public class DaoTest extends BaseDaoTest{

    @Autowired
    @Qualifier("applicantDao")
    private ApplicantDao applicantDao;

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("id1");
        assertEquals(expectedApplicant,applicantDao.getApplicantById(applicantId));
    }

    @Test
    public void testGetApplicantByNotExistingIdAndExpectNull()throws Exception{
        String applicantId = "id4";
        assertEquals(null,applicantDao.getApplicantById(applicantId));
    }
}
