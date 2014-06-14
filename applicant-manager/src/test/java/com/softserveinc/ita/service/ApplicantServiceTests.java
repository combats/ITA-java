package com.softserveinc.ita.service;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

public class ApplicantServiceTests extends BaseMVCTest {

    @Autowired
    private ApplicantService applicantService;

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant editedApplicant = applicantService.editApplicant(newApplicant);
        assertEquals(newApplicant.getId(), editedApplicant.getId());
    }
}
