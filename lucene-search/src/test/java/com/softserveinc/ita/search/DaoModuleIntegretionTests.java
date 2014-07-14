package com.softserveinc.ita.search;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.search.BaseTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DaoModuleIntegretionTests extends BaseTest {

    @Autowired
    private ApplicantDAO applicantDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testGetApplicants() {
        Session session = sessionFactory.getCurrentSession();
        session.save(new Applicant("TestApplicantOne", "Surname"));
        session.save(new Applicant("TestApplicantTwo", "Surname"));
        session.save(new Applicant("TestApplicantThree", "Surname"));
        List<Applicant> applicants = applicantDAO.getApplicants();
        int actualSize = applicants.size();
        assertEquals(3, actualSize);
    }
}
