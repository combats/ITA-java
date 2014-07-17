package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.impl.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ApplicantServiceMockitoTest extends BaseApplicantServiceTest {
    private List<Applicant> standartList;
    private List<Applicant> applicants;

    @Autowired
    @InjectMocks
    private ApplicantService applicantService;

    @Autowired
    @Mock
    private ApplicantDAO applicantDao;

    public ApplicantServiceMockitoTest() {
        standartList = new ArrayList<>();
        standartList.add(new Applicant("123"));
        standartList.add(new Applicant("124"));
        standartList.add(new Applicant("125"));
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplicantServiceGetApplicantsListAndExpectedIsEquallity() {
        when(applicantDao.getApplicants()).thenReturn(standartList);
        assertEquals(standartList, applicantService.getApplicants());
        verify(applicantDao, times(1)).getApplicants();
    }

    @Test
    public void testGetApplicantsInGroupAndExpectediIsOk() {
        List<Applicant> mockApplicants = new ArrayList<>();
        mockApplicants.add(new Applicant("123"));
        mockApplicants.add(new Applicant("124"));
        mockApplicants.add(new Applicant("125"));

        when(applicantDao.getApplicantsByGroupID("1")).thenReturn(mockApplicants);
        applicants = applicantService.getApplicantsByGroupID("1");
        verify(applicantDao, times(1)).getApplicantsByGroupID("1");
        assertEquals(applicants, mockApplicants);
    }

    @Test
    public void testGetApplicantsInGroupAndExceptionExpected() {
        List<Applicant> mockApplicants = new ArrayList<>();
        when(applicantDao.getApplicantsByGroupID("2")).thenReturn(mockApplicants);
        applicants = applicantService.getApplicantsByGroupID("2");
        verify(applicantDao, times(1)).getApplicantsByGroupID("2");
        assertEquals(applicants, mockApplicants);
    }
}
