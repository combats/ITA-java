package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant receivedApplicant = applicantService.getApplicantById(newApplicant.getApplicantID());
        assertEquals(newApplicant, receivedApplicant);
    }

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant editedApplicant = applicantService.editApplicant(newApplicant);
        assertEquals(newApplicant.getApplicantID(), editedApplicant.getApplicantID());
    }

    @Test
    public void testGetApplicantIDListAndExpectDefinedValues() throws Exception {
        List<String> expectedApplicantIDList = new ArrayList<>();
        Collections.addAll(expectedApplicantIDList, "123", "124", "125");
        assertEquals(expectedApplicantIDList, applicantService.getApplicantIDList());
    }
}
