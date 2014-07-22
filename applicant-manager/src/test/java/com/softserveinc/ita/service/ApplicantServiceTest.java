package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApplicantServiceTest extends BaseApplicantServiceTest {

    @Autowired
    ApplicantService applicantService;
    private List<Applicant> allApplicants = new ArrayList<>();

    @Before
    private void init() {
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
        Collections.addAll(allApplicants, app1, app2, app3, app4, app5);
    }

    @Test
    public void testGetApplicantByExistingIdAndExpectEquals() throws Exception {
        String applicantId = "id1";
        Applicant expectedApplicant = allApplicants.get(0);//new Applicant("id1");
        assertEquals(expectedApplicant, applicantService.getApplicantById(applicantId));
    }

    @Test(expected = ApplicantDoesNotExistException.class)
    public void testGetApplicantByNotExistingIdAndExpectException() throws Exception {
        String applicantId = "id4";
        applicantService.getApplicantById(applicantId);
    }

    @Test
    public void testAddApplicantAndGetTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant receivedApplicant = applicantService.getApplicantById(newApplicant.getID());
        assertEquals(newApplicant, receivedApplicant);
    }

    @Test
    public void testAddApplicantAndEditTheSameApplicant() throws Exception {
        Applicant newApplicant = applicantService.addNewApplicant(new Applicant());
        Applicant editedApplicant = applicantService.editApplicant(newApplicant);
        assertEquals(newApplicant.getID(), editedApplicant.getID());
    }

    @Test
    public void testGetApplicantIDListAndExpectDefinedValues() throws Exception {
        List<String> expectedApplicantIDList = new ArrayList<>();
        Collections.addAll(expectedApplicantIDList, "123", "124", "125");
        assertEquals(expectedApplicantIDList, applicantService.getApplicantIDList());
    }
}
