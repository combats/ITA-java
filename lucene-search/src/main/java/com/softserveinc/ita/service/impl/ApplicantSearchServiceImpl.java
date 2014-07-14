package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.ApplicantSearchService;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicantSearchServiceImpl implements ApplicantSearchService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Applicant> getApplicantsByName(String wildcard) {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);

        Transaction tx = fullTextSession.beginTransaction();

        org.apache.lucene.search.Query luceneQuery = new WildcardQuery(new Term("name",wildcard));

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Applicant.class);
        List<Applicant> applicants = fullTextQuery.list();

        tx.commit();
        session.close();

        return applicants;
    }

    @Override
    public List<Applicant> getApplicantsByLastName(String wildcard) {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);

        Transaction tx = fullTextSession.beginTransaction();

        org.apache.lucene.search.Query luceneQuery = new WildcardQuery(new Term("surname",wildcard));

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Applicant.class);
        List<Applicant> applicants = fullTextQuery.list();

        tx.commit();
        session.close();

        return applicants;
    }
}
