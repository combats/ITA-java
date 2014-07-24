package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public Group getGroupById(String groupId) {
        return groupDao.getGroupById(groupId);
    }

    @Override
    public List<Group> getGroupsByStatus(Group.Status groupStatus) {
        List<Group> groups = groupDao.getAllGroups();
        ArrayList<Group> choosenGroups = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        for (Group group : groups) {
            switch (groupStatus) {
                case PLANNED:
                    if (currentTime < group.getStartBoardingTime()) {
                        choosenGroups.add(group);
                    }
                    break;
                case BOARDING:
                    if (currentTime > group.getStartBoardingTime() && currentTime < group.getStartTime()) {
                        choosenGroups.add(group);
                    }
                    break;
                case IN_PROCESS:
                    if (currentTime > group.getStartTime() && currentTime < group.getEndTime()) {
                        choosenGroups.add(group);
                    }
                    break;
                case FINISHED:
                    if (currentTime > group.getEndTime()) {
                        choosenGroups.add(group);
                    }
                    break;

            }
        }
        return choosenGroups;
    }

    @Override
    public List<Course> getCourses() {
        return groupDao.getCourses();
    }

    @Override
    public List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException {
        return groupDao.getApplicantsByGroupID(groupID);
    }

    @Override
    public Group createGroup(Group group) {
        return groupDao.addGroup(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    private boolean isWrongStatus(Group.Status groupStatus) {
        for (Group.Status status : Group.Status.values()) {
            if (status.equals(groupStatus)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getGroupCapacity(String groupId) {
        return groupDao.getGroupById(groupId).getCapacity();
    }

    @Override
    public Map<String, ApplicantBenchmark> getApplicantsByStatus(String groupId, Applicant.Status status) {
        Group group = groupDao.getGroupById(groupId);
        Map<String, ApplicantBenchmark> result = new HashMap<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : group.getApplicants().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Override
    public void addOrUpdateApplicantIDListByStatus(String groupId, Map<String, ApplicantBenchmark> applicants) {
        Group group = groupDao.getGroupById(groupId);
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            group.getApplicants().put(entry.getKey(), entry.getValue());
        }
    }
}
