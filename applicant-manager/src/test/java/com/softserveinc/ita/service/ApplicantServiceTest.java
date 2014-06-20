package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class ApplicantServiceTest extends BaseApplicantServiceTest {

    @Autowired
    ApplicantService applicantService;

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("id1");
        assertEquals(expectedApplicant,applicantService.getApplicantById(applicantId));
    }

    @Test(expected = ApplicantDoesNotExistException.class)
    public void testGetApplicantByNotExistingIdAndExpectException() throws Exception {
        String applicantId = "id4";
        applicantService.getApplicantById(applicantId);
    }
}
