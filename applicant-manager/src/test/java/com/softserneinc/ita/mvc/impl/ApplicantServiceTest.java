package com.softserneinc.ita.mvc.impl;


import com.softserneinc.ita.mvc.BaseMVCTest;
import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.ApplicantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicantServiceTest extends BaseMVCTest {
    List<Applicant> standartList;

    @Autowired
    private ApplicantService applicantService;

    @Mock
    private ApplicantDao applicantDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplicantServiceGetApplicantsListAndExpectedIsEquallity() {
        standartList = new ArrayList<>();
        standartList.add(new Applicant("123"));
        standartList.add(new Applicant("124"));
        standartList.add(new Applicant("125"));

        when(applicantDao.getApplicants()).thenReturn(standartList);

        assertEquals(standartList, applicantService.getApplicants());
    }
}
