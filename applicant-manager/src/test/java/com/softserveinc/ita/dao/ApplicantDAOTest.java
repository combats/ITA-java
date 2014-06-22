package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class ApplicantDAOTest extends BaseApplicantDAOTest {

    @Autowired
    private ApplicantDAO applicantDao;

    @Test
    public void testGetApplicantsListAndExpectedIsOk() throws GroupNotFoundException {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("123"));
        applicants.add(new Applicant("124"));
        applicants.add(new Applicant("125"));
        assertTrue(applicantDao.getApplicants().containsAll(applicants));
        assertEquals(applicants, applicantDao.getApplicantsInAGroup("1"));
    }

    @Test(expected = GroupNotFoundException.class)
    public void testGetApplicantsInGroupAndExceptionExpected() throws GroupNotFoundException {
        List<Applicant>applicantList = applicantDao.getApplicantsInAGroup("2");
}

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

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantDao.addNewApplicant(new Applicant());
        assertNotNull(newApplicant);
        assertFalse(newApplicant.getApplicantID().isEmpty());
        Applicant receivedApplicant = applicantDao.getApplicantById(newApplicant.getApplicantID());
        assertEquals(newApplicant, receivedApplicant);
    }

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantDao.addNewApplicant(new Applicant());
        assertNotNull(newApplicant);
        assertFalse(newApplicant.getApplicantID().isEmpty());
        Applicant editedApplicant = applicantDao.editApplicant(newApplicant);
        assertEquals(newApplicant.getApplicantID(), editedApplicant.getApplicantID());
    }
}
