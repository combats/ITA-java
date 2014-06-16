package com.softserveinc.ita.dao;

import com.softserveinc.ita.DAO.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOTest extends BaseDAOTest {
    @Autowired
    private ApplicantDAO applicantDAOImpl;

    @Test
    public void testGetApplicantListAndExpectDefinedValues() throws Exception {
        List<Applicant> expectedApplicantList = new ArrayList<>();
        Collections.addAll(expectedApplicantList, new Applicant("id1"), new Applicant("id2"), new Applicant("idX"));
        Assert.assertEquals(expectedApplicantList, applicantDAOImpl.getApplicants());
    }

}
