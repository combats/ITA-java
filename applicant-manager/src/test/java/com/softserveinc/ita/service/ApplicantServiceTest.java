package com.softserveinc.ita.service;

import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class ApplicantServiceTest extends BaseServiceTest{

    @Autowired
    private ApplicantService applicantServiceImpl;

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("id1");
        assertEquals(expectedApplicant,applicantServiceImpl.getApplicantById(applicantId));
    }

    @Test(expected = ApplicantDoesNotExistException.class)
    public void testGetApplicantByNotExistingIdAndExpectException() throws Exception {
        String applicantId = "id4";
        applicantServiceImpl.getApplicantById(applicantId);
    }
}
