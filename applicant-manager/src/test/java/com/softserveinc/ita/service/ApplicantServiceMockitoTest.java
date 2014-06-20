package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.Mock;
import com.softserveinc.ita.exception.GroupNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicantServiceMockitoTest extends BaseApplicantServiceTest {
    private List<Applicant> standartList;
    List<Applicant> applicants;

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
        verify(applicantDao).getApplicants();
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
