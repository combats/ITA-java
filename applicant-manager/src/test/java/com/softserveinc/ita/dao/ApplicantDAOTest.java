package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.impl.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.*;

public class ApplicantDAOTest extends BaseApplicantDAOTest {

    @Autowired
    private ApplicantDAO applicantDao;

    @Test
    public void testGetApplicantsListAndExpectedIsOk() throws GroupNotFoundException {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("123", "Vasya", "Vasya", "Vasya@mail.ru"));
        applicants.add(new Applicant("124", "Vasya", "Vasya", "Vasya@mail.ru"));
        applicants.add(new Applicant("125", "Vasya", "Vasya", "Vasya@mail.ru"));
//        assertTrue(applicantDao.getApplicants().containsAll(applicants));
        assertEquals(applicants, applicantDao.getApplicantsByGroupID("1"));
    }

    @Test()
    public void testGetApplicantsInGroupAndExceptionExpected() {
        List<Applicant> emptyList = new ArrayList<>();
        List<Applicant> applicantList = applicantDao.getApplicantsByGroupID("2");
        assertEquals(emptyList, applicantList);
    }

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("id1");
        assertEquals(expectedApplicant, applicantDao.getApplicantById(applicantId));
    }

    @Test
    public void testGetApplicantByNotExistingIdAndExpectNull() throws Exception {
        String applicantId = "id4";
        assertEquals(null, applicantDao.getApplicantById(applicantId));
    }

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantDao.addNewApplicant(new Applicant());
        assertNotNull(newApplicant);
        assertFalse(newApplicant.getId().isEmpty());
        Applicant receivedApplicant = applicantDao.getApplicantById(newApplicant.getId());
        assertEquals(newApplicant, receivedApplicant);
    }

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantDao.addNewApplicant(new Applicant());
        assertNotNull(newApplicant);
        assertFalse(newApplicant.getId().isEmpty());
        Applicant editedApplicant = applicantDao.editApplicant(newApplicant);
        assertEquals(newApplicant.getId(), editedApplicant.getId());
    }

    @Test
    public void testGetApplicantListAndExpectDefinedValues() throws Exception {
        List<Applicant> expectedApplicantList = new ArrayList<>();
        Collections.addAll(expectedApplicantList,
                new Applicant("123", "Vasya", "Vasya", "Vasya@mail.ru"),
                new Applicant("124", "Vasya", "Vasya", "Vasya@mail.ru"),
                new Applicant("125", "Vasya", "Vasya", "Vasya@mail.ru"));
        assertEquals(expectedApplicantList, applicantDao.getApplicants());
    }
}
