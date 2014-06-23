package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.GroupNotFoundException;
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

    public ApplicantDAOMockImpl() {
        applicants.put("123", new Applicant("123"));
        applicants.put("124", new Applicant("124"));
        applicants.put("125", new Applicant("125"));
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
        return new ArrayList<Applicant>(applicants.values());
    }

    @Override
    public List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException {

        if (db.containsKey(groupID)) {
            if (!applicantsInAGroup.equals((db.get(groupID)).getApplicantsInGroup())) {
                throw new GroupNotFoundException();
            }
            return (db.get(groupID)).getApplicantsInGroup();
        } else {
            throw new GroupNotFoundException();
        }
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
        applicant.setApplicantID(newApplicantId);
        applicants.put(newApplicantId, applicant);
        return applicant;
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        String applicantForEditId = applicant.getApplicantID();
        applicants.put(applicantForEditId, applicant);
        return applicant;
    }
}