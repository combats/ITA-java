package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.GroupNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicantDaoImpl implements ApplicantDao {
    private Map<String, Group> db;
    private List<Applicant> applicantsInAGroup;
    private Group group;

    public ApplicantDaoImpl() {
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
}
