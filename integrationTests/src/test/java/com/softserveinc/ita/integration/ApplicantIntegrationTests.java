package com.softserveinc.ita.integration;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.utils.JsonUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.07.14
 * Time: 2:59
 * To change this template use File | Settings | File Templates.
 */
public class ApplicantIntegrationTests extends BaseIntegrationTests {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Test
    public void testPostNewApplicantAndExpectEqualOne() throws Exception {

        Applicant applicant = new Applicant();
        applicant.setName("Vasya");
        applicant.setSurname("Pupkin");

        String applicantJson = jsonUtil.toJson(applicant);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(applicantJson,headers);
        Applicant applicant1 = httpRequestExecutor.postNewObject("applicants/", entity, Applicant.class);
        String applicantId = applicant1.getId();

        Applicant expectedAplicant = httpRequestExecutor.getObjectByID(applicantId, Applicant.class);
        applicant.setId(applicantId);
        Assert.assertEquals(applicant, expectedAplicant);

    }

}
