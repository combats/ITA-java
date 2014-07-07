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
    private ApplicantService applicantService;

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        Applicant expectedApplicant = new Applicant("Vasya", "Vasya", "Vasya@mail.ru", "1");
        expectedApplicant.setId("1");
        assertEquals(expectedApplicant,applicantService.getApplicantById("id1"));
    }

    @Test(expected = ApplicantDoesNotExistException.class)
    public void testGetApplicantByNotExistingIdAndExpectException() throws Exception {
        String applicantId = "id4";
        applicantService.getApplicantById(applicantId);
    }

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant receivedApplicant = applicantService.getApplicantById(newApplicant.getId());
        assertEquals(newApplicant, receivedApplicant);
    }

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant editedApplicant = applicantService.editApplicant(newApplicant);
        assertEquals(newApplicant.getId(), editedApplicant.getId());
    }

    @Test
    public void testGetApplicantIDListAndExpectDefinedValues() throws Exception {
        List<String> expectedApplicantIDList = new ArrayList<>();
        Collections.addAll(expectedApplicantIDList, "1", "2", "3");
        assertEquals(expectedApplicantIDList, applicantService.getApplicantIDList());
    }
}
