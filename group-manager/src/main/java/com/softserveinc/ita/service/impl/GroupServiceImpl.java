package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.WrongGroupStatusException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDAO;

    @Override
    public Group getGroupById(String groupId) {
        return groupDAO.getGroupById(groupId);
    }

    @Override
    public List<Group> getGroupsByStatus(String groupStatus) {
        if (isWrongStatus(groupStatus)) {
            throw new WrongGroupStatusException();
        }
        return groupDAO.getGroupsByStatus(groupStatus);
    }

    @Override
    public List<Applicant> getApplicantsByGroupID(String groupID) {
        return groupDAO.getApplicantsByGroupID(groupID);
    }

    @Override
    public int getGroupCapacity(String groupId) {
        return groupDAO.getGroupById(groupId).getCapacity();
    }

    @Override
    public Map<String, ApplicantBenchmark> getApplicantsByStatus(String groupId, Applicant.Status status) {
        Group group = groupDAO.getGroupById(groupId);
        Map<String, ApplicantBenchmark> result = new HashMap<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : group.getApplicantsInGroup().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Override
    public void addOrUpdateApplicantIDListByStatus(String groupId, Map<String, ApplicantBenchmark> applicants) {
        Group group = groupDAO.getGroupById(groupId);
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            group.getApplicantsInGroup().put(entry.getKey(), entry.getValue());
        }
    }

    private boolean isWrongStatus(String groupStatus) {
        for (Group.Status status : Group.Status.values()) {
            if (status.getName().equals(groupStatus)) {
                return false;
            }
        }
        return true;
    }
}
