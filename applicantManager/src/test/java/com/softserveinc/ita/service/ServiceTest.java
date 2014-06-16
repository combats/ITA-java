package com.softserveinc.ita.service;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceTest extends BaseServiceTest {
    @Autowired
    private ApplicantService applicantServiceImpl;

    @Test
    public void testGetApplicantIDListAndExpectDefinedValues() throws Exception {
        List<String> expectedApplicantIDList = new ArrayList<>();
        Collections.addAll(expectedApplicantIDList, "id1", "id2", "idX");
        Assert.assertEquals(expectedApplicantIDList, applicantServiceImpl.getApplicantIDList());
    }
}
