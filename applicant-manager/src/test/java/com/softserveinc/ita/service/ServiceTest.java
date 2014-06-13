package com.softserveinc.ita.service;

import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static junit.framework.Assert.assertEquals;

public class ServiceTest extends BaseServiceTest{

    @Autowired
    @Qualifier("applicantService")
    private ApplicantService applicantService;

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
