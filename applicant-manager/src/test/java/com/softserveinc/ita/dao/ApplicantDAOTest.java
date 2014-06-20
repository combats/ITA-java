package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class ApplicantDaoTest extends BaseApplicantDaoTest {

    @Autowired
    private ApplicantDao applicantDAOMockImpl;

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("id1");
        assertEquals(expectedApplicant,applicantDAOMockImpl.getApplicantById(applicantId));
    }

    @Test
    public void testGetApplicantByNotExistingIdAndExpectNull()throws Exception{
        String applicantId = "id4";
        assertEquals(null,applicantDAOMockImpl.getApplicantById(applicantId));
    }
}
