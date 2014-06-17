package com.softserveinc.ita.service;


import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicantServiceTest extends BaseApplicantServiceTest {
    private List<Applicant> standartList;

    @Autowired
    @InjectMocks
    private ApplicantService applicantService;

    @Autowired
    @Mock
    private ApplicantDao applicantDao;

    public ApplicantServiceTest() {
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
}
