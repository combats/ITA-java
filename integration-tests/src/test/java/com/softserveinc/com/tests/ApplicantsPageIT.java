package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.UIApplicantsPage;
import org.junit.Test;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class ApplicantsPageIT extends BaseUIIntegrationTest{
    private static final String GROUP_NAME = "KV-009";
    private static final String ADDRESS = "unknown address";
    private static final String COURSE = "Java"; // available now values: "Java", ".NET", "DevOps", "JavaScript".
    private static final String CAPACITY = "12";
    private static final String START_BOARDING_DATE = "08/12/2014"; // must be like MM/dd/yyyy and greater than today's date
    private static final String START_DATE = "08/06/2015"; // must be bigger than boarding date
    private static final String START_TIME = "11:00";
    private static final String END_DATE = "11/14/2015";

    private static final String APPL_NAME = "Ololo";
    private static final String APPL_SURNAME = "Trololo";
    private static final String PHONE_NUMB = "0963215879";
    private static final String EMALE = "Lololo.Ololo.Trololo@gmail.com";
    private static final String BIRTH_DATE = "09/15/2013";

    private String ApplCv;

    public void setApplicantCvPath(String extension) {
        String resName = "CV/testCV." + extension;
        URL url = this.getClass().getResource("/" + resName);
        ApplCv = url.toString();
    }


    @Test
    public void testAddGroupAndGoToApplicants() {
        getDrv().get(getSiteBase().toString());

        String ID = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups()
                .addGroup(GROUP_NAME,
                          ADDRESS,
                          COURSE,
                          CAPACITY,
                          START_BOARDING_DATE,
                          START_DATE,
                          START_TIME,
                          END_DATE)
                .goToApplicants(GROUP_NAME).getGroupID();

        assertTrue(UIApplicantsPage.isAtApplicantsPage(getDrv(), getSiteBase(), ID)); //because of # in the end
    }

    @Test
    public void testAddApplicant() {
        getDrv().get(getSiteBase().toString());
        setApplicantCvPath("docx");

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(APPL_NAME, APPL_SURNAME, PHONE_NUMB, EMALE, BIRTH_DATE, ApplCv);

        assertTrue(getDrv().getPageSource().contains(APPL_NAME));
    }

    @Test
    public void testEditApplicantsInterview() throws InterruptedException {
        getDrv().get(getSiteBase().toString());
        setApplicantCvPath("docx");

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME).setInterviewDate(APPL_NAME, APPL_SURNAME, EMALE);

    }
}
