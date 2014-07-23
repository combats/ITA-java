package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.impl.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
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
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = new Applicant("Name", "Lastname", "email@mail.com", "1234567894568", 98736513843543L);
        expectedApplicant.setId(applicantId);
        assertEquals(expectedApplicant, applicantDao.getApplicantById(applicantId));
    }

    @Test
    public void testGetApplicantByNotExistingIdAndExpectNull() throws Exception {
        String applicantId = "id44";
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
        Applicant app1 = new Applicant("Name", "Lastname", "email@mail.com", "1234567894568", 98736513843543L);
        app1.setId("id1");
        Applicant app2 = new Applicant("Andrey", "Makarevich", "email@mail.com", "1234567894568", 98736513843543L);
        app2.setId("id2");
        Applicant app3 = new Applicant("Alexandr", "Drux", "email@mail.com", "1234567894568", 98736513843543L);
        app3.setId("id3");
        Collections.addAll(expectedApplicantList, app1, app2, app3);
        assertEquals(expectedApplicantList, applicantDao.getApplicants());
    }
}