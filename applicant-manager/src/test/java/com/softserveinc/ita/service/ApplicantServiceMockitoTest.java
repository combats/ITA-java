package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.dao.impl.ApplicantDAOMockImpl;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.impl.ApplicantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicantServiceMockitoTest extends BaseApplicantServiceTest {
    private List<Applicant> standartList;
    private List<Integer> applicants;

    //  @Autowired
    @InjectMocks
    private ApplicantService applicantService = new ApplicantServiceImpl();//TODO:Find out why there are problems with Autowired.

    //@Autowired
    @Mock
    private ApplicantDAO applicantDao = new ApplicantDAOMockImpl(); //TODO:Find out why there are problems with Autowired.

    public ApplicantServiceMockitoTest() {
        standartList = new ArrayList<>();
        Applicant app1 = new Applicant("1", "Alexander", "Druz", "9379992",
                "druz@gmail.com", 16843654863L);
        Applicant app2 =
                new Applicant("2", "Andrey", "Makarevich", "0671233215",
                        "makarevich@gmail.com", 4368413654L);
        Applicant app3 =
                new Applicant("3", "Anatoliy", "Vasserman", "111111",
                        "vasserman@gmail.com", 123643968L);
        Applicant app4 =
                new Applicant("4", "Nikita", "Dzhigurda", "1319758",
                        "dzhigurda@gmail.com", 439873156L);
        Applicant app5 =
                new Applicant("5", "Alexandr", "Maslyakov", "368413",
                        "kvn@gmail.com", 498789635L);
        Applicant app6 =
                new Applicant("6", "Michael", "Jackson", "7894395",
                        "MJ@gmail.com", 123635486L);
        Applicant app7 =
                new Applicant("7", "Tim", "Howard", "16357453",
                        "Howard@gmail.com", 3338415446L);
        Collections.addAll(standartList, app1, app2, app3, app4, app5);
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
    public void testGetApplicantsInGroupAndExpectediIsOk() {
        List<Applicant> mockApplicants = new ArrayList<>();
        Applicant app1 = new Applicant("1", "Alexander", "Druz", "9379992",
                "druz@gmail.com", 16843654863L);
        Applicant app2 =
                new Applicant("2", "Andrey", "Makarevich", "0671233215",
                        "makarevich@gmail.com", 4368413654L);
        Applicant app3 =
                new Applicant("3", "Anatoliy", "Vasserman", "111111",
                        "vasserman@gmail.com", 123643968L);
        Applicant app4 =
                new Applicant("4", "Nikita", "Dzhigurda", "1319758",
                        "dzhigurda@gmail.com", 439873156L);
        Applicant app5 =
                new Applicant("5", "Alexandr", "Maslyakov", "368413",
                        "kvn@gmail.com", 498789635L);
        Applicant app6 =
                new Applicant("6", "Michael", "Jackson", "7894395",
                        "MJ@gmail.com", 123635486L);
        Applicant app7 =
                new Applicant("7", "Tim", "Howard", "16357453",
                        "Howard@gmail.com", 3338415446L);
        Collections.addAll(mockApplicants, app1, app2, app3, app4, app5);
//        when(applicantDao.getApplicantsByGroupID("1")).thenReturn(mockApplicants);
        applicants = applicantService.getApplicantsByGroupID("1");
        verify(applicantDao).getApplicantsByGroupID("1");
        assertEquals(applicants, mockApplicants);
    }

    @Test
    public void testGetApplicantsInGroupAndExceptionExpected() {
        List<Applicant> mockApplicants = new ArrayList<>();
//        when(applicantDao.getApplicantsByGroupID("2")).thenReturn(mockApplicants);
        applicants = applicantService.getApplicantsByGroupID("2");
        verify(applicantDao).getApplicantsByGroupID("2");
        assertEquals(applicants, mockApplicants);
    }
}
