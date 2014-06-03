package com.softserveinc.ita.dao.impl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Hashtable;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import org.springframework.stereotype.Repository;

/**
 *  temporary mock implementation of the persistence layer
 **/
@Repository
public class ApplicantDaoMockImpl implements ApplicantDao {

    private AtomicInteger idAutoGeneration = new AtomicInteger();
    private Hashtable<String, Applicant> dbDaoMock = new Hashtable<String, Applicant>();

    public Applicant addNewApplicant(Applicant applicant){
        /**
         *  because of Applicant pojo doesn't contain any fields except id now, validation
         *  for not having equal object in DB has not been implemented
         **/
        String newApplicantId = String.valueOf(idAutoGeneration.incrementAndGet());
        applicant.setId(newApplicantId);
        dbDaoMock.put(newApplicantId, applicant);
        return applicant;
    }

    public Applicant getApplicantById(String applicantId){
        return dbDaoMock.get(applicantId);
    }
}
