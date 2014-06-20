package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import java.util.ArrayList;
import java.util.List;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.GroupNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApplicantDAOMockImpl implements ApplicantDAO {
    List<Applicant> applicants;
    private Map<String, Group> db;
    private List<Applicant> applicantsInAGroup;
    private Group group;

    public ApplicantDAOMockImpl() {
        applicants = new ArrayList<>();
        applicants.add(new Applicant("123"));
        applicants.add(new Applicant("124"));
        applicants.add(new Applicant("125"));

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
        return applicants;
    }

    @Override
    public List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException {

        if(db.containsKey(groupID)) {
            if(!applicantsInAGroup.equals((db.get(groupID)).getApplicantsInGroup())) {
                throw new GroupNotFoundException();
            }
            return (db.get(groupID)).getApplicantsInGroup();
        }
        else {
            throw new GroupNotFoundException();
        }
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        List<Applicant> applicants = new ArrayList() {
            {
                add(new Applicant("id1"));
                add(new Applicant("id2"));
                add(new Applicant("id3"));
            }
        };
        for (Applicant applicant : applicants) {
            if (applicantId.equals(applicant.getApplicantID())) {
                return applicant;
            }
        }
        return null;
    }
}
