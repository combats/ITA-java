package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-test-context.xml"})
public class ApplicantDAOTests extends BaseDAOTest {

    @Autowired
    private ApplicantDAO applicantDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testGetApplicants() {
        Session session = sessionFactory.getCurrentSession();
        session.save(new Applicant("TestApplicantOne", "Group"));
        session.save(new Applicant("TestApplicantTwo", "Group"));
        session.save(new Applicant("TestApplicantThree", "Group"));
        List<Applicant> applicants = applicantDAO.getApplicants();
        int actualSize = applicants.size();
        assertEquals(3, actualSize);
    }

    @Test
    public void testGetApplicantsInAGroup() {
        Session session = sessionFactory.getCurrentSession();
        session.save(new Applicant("TestApplicant", "Group1"));
        session.save(new Applicant("TestApplicant", "Group1"));
        session.save(new Applicant("TestApplicant", "Group2"));
        session.flush();
        List<Applicant> inAGroup = applicantDAO.getApplicantsInAGroup("Group1");
        assertThat(2, equalTo(inAGroup.size()));
    }

    @Test
    public void testGetApplicantById() {
        Session session = sessionFactory.getCurrentSession();
        Applicant expected = new Applicant("TestApplicant", "Group");
        String applicantId = (String) session.save(expected);
        Applicant actual = applicantDAO.getApplicantById(applicantId);
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void testAddNewApplicant() {
        Applicant expected = new Applicant("TestApplicant", "Group");
        Applicant actual = applicantDAO.addNewApplicant(expected);
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void testEditApplicant() {
        Applicant expected = new Applicant("ExpectedApplicant", "Group");
        sessionFactory.getCurrentSession().save(expected);
        expected.setName("Actual");
        Applicant actual = applicantDAO.editApplicant(expected);
        assertThat(expected, equalTo(actual));
    }
}
