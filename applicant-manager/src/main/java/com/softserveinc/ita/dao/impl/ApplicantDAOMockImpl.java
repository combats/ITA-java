package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ApplicantDAOMockImpl implements ApplicantDAO {
    private Map<String, Group> db;
    private List<Applicant> applicantsInAGroup;
    private Group group;
    private AtomicInteger idAutoGeneration = new AtomicInteger();
    private Hashtable<String, Applicant> applicants = new Hashtable<String, Applicant>();
    private List<Applicant> allApplicants = new ArrayList<>();

    public ApplicantDAOMockImpl() {
        allApplicants.add(new Applicant("123"));
        allApplicants.add(new Applicant("124"));
        allApplicants.add(new Applicant("125"));

        applicants.put("id1", new Applicant("id1"));
        applicants.put("id2", new Applicant("id2"));
        applicants.put("id3", new Applicant("id3"));

        db = new HashMap<>();
        applicantsInAGroup = new ArrayList<>();
        group = new Group("1");
        applicantsInAGroup.add(new Applicant("123"));
        applicantsInAGroup.add(new Applicant("124"));
        applicantsInAGroup.add(new Applicant("125"));
        group.setApplicantsInGroup(applicantsInAGroup);
        db.put(group.getGroupID(), group);
    }

    @Override
    public List<Applicant> getApplicants() {
        return allApplicants;
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return applicants.get(applicantId);
    }

    @Override
    public Applicant addNewApplicant(Applicant applicant) {
        /**
         *  because of Applicant pojo doesn't contain any fields except id now, validation
         *  for not having equal object in DB has not been implemented
         **/
        String newApplicantId = String.valueOf(idAutoGeneration.incrementAndGet());
        applicant.setId(newApplicantId);
        applicants.put(newApplicantId, applicant);
        return applicant;
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        String applicantForEditId = applicant.getId();
        applicants.put(applicantForEditId, applicant);
        return applicant;
    }
}