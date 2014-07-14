package com.softserveinc.ita.search;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.search.BaseTest;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HibernateSearchTests extends BaseTest {

    @Autowired
    private ApplicantDAO applicantDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Test
    public void testGetApplicants() {
        Session session = sessionFactory.getCurrentSession();
        session.save(new Applicant("TestApplicantOne", "abcdefg"));
        session.save(new Applicant("TestApplicantTw", "hijklmnop"));
        session.save(new Applicant("TestApplicantThre", "qrstuv"));

//        session.getTransaction().begin();

        List<Applicant> list =  (List<Applicant>)session.createCriteria(Applicant.class).list();

            FullTextSession fullTextSession = Search.getFullTextSession(session);
            try {
                fullTextSession.createIndexer().startAndWait();
            }catch (InterruptedException ex){
                System.err.println("Error creating indexer " + ex.getMessage());
        }

        session.getTransaction().commit();

        // create native Lucene query unsing the query DSL
// alternatively you can write the Lucene query using the Lucene query parser
// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        String searchStr = "testapplicantone";
        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Applicant.class).get();

//        org.apache.lucene.search.Query luceneQuery = qb
//                .keyword()
//                .onFields("name")
//                .matching(searchStr)
//                .createQuery();
        org.apache.lucene.search.Query luceneQuery = new WildcardQuery(new Term("name","t*"));

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Applicant.class);

// execute search
        List<Applicant> result = fullTextQuery.list();

        //List<Applicant> applicants = applicantDAO.getApplicants();
        int actualSize = result.size();
        assertEquals(1, actualSize);
    }
}