package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicantServiceTest extends BaseApplicantServiceTest {
    List<Applicant> applicants;

    @Autowired
    @InjectMocks
    private ApplicantService applicantService;

    @Autowired
    @Mock
    private ApplicantDao applicantDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApplicantsInGroupAndExpectediIsOk() throws GroupNotFoundException {
        List<Applicant> mockApplicants = new ArrayList<>();
        mockApplicants.add(new Applicant("123"));
        mockApplicants.add(new Applicant("124"));
        mockApplicants.add(new Applicant("125"));

        when(applicantDao.getApplicantsInAGroup("1")).thenReturn(mockApplicants);
        applicants = applicantService.getApplicantsInAGroup("1");
        verify(applicantDao).getApplicantsInAGroup("1");
        assertEquals(applicants, mockApplicants);
    }

    @Test(expected = GroupNotFoundException.class)
    public void testGetApplicantsInGroupAndExceptionExpected() throws GroupNotFoundException {

        when(applicantDao.getApplicantsInAGroup("2")).thenThrow(new GroupNotFoundException());
        applicants = applicantService.getApplicantsInAGroup("2");
    }
}
