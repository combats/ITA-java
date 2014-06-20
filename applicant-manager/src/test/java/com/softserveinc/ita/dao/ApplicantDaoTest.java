package com.softserveinc.ita.dao;

import com.softserveinc.ita.BaseITATest;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

public class ApplicantDaoTest extends BaseITATest {

    @Autowired
    private ApplicantDao applicantDao;

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantDao.addNewApplicant(new Applicant());
        assertNotNull(newApplicant);
        assertFalse(newApplicant.getId().isEmpty());
        Applicant receivedApplicant = applicantDao.getApplicantById(newApplicant.getId());
        assertEquals(newApplicant, receivedApplicant);
    }
}
