package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.BaseTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ApplicantSearchServiceTests extends BaseTest {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ApplicantSearchService service;

    private boolean setUpIsDone = false;
    @Before
    public void setup() {
        //Used Before with flag instead of BeforeClass to avoid static initialization.
        if (setUpIsDone) {
            return;
        }
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(new Applicant("John", "Doe"));
        session.save(new Applicant("Lola", "Stimorola"));
        session.save(new Applicant("Tony", "Ballony"));
        session.save(new Applicant("Caroline", "Woods"));
        session.save(new Applicant("Cinderella", "Hopkins"));
        session.save(new Applicant("George", "Underlobster"));
        session.save(new Applicant("Kelly", "Mitsubishi"));
        session.getTransaction().commit();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
            try {
                fullTextSession.createIndexer().startAndWait();
            }catch (InterruptedException ex){
                System.err.println("Error creating search indexer: " + ex.getMessage());
            }
        setUpIsDone = true;
        session.close();
    }

    @Test
    public void testSearchApplicantByNameReturnsProperApplicant() {
        List<Applicant> result = service.getApplicantsByName("john");
        assertEquals(result.get(0).getName(), "John");
    }
    @Test
    public void testSearchApplicantByLastNameReturnsProperApplicant() {
        List<Applicant> result = service.getApplicantsByName("doe");
        assertEquals(result.get(0).getName(), "Doe");
    }
    @Test
    public void testSearchApplicantByLastNameReturnsProperApplicants() {
        List<Applicant> result = service.getApplicantsByName("*s");
        assertEquals(result.size(), 2);
    }
}