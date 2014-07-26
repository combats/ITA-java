package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDAO;
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
    private GroupDAO groupDAO;

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> getGroupsByStatus(Group.Status groupStatus) {
        List<Group> groups = groupDao.getAllGroups();
        List<Group> choosenGroups = new ArrayList<>();
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
    public ArrayList<Course> getCourses() {
        return groupDao.getCourses();
    }

    @Override
    public Group createGroup(Group group) {
        return groupDao.addGroup(group);
    }

    @Override
    public ArrayList<Group> getAllGroups() {
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
    public List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException {
        return groupDao.getApplicantsByGroupID(groupID);
    }

    @Override
    public int getGroupCapacity(String groupID) {
        return groupDAO.getGroupBiId(groupID).getCapacity();
    }

    @Override
    public Group updateApplicantsInGroup(String groupID,
                                         Map<String, ApplicantBenchmark> applicants) {
        Group group = groupDAO.getGroupBiId(groupID);
        Map<String, ApplicantBenchmark> groupApplicants = group.getApplicants();
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            groupApplicants.put(entry.getKey(), entry.getValue());
        }
        return groupDAO.updateGroup(group);
    }

    @Override
    public Map<String, ApplicantBenchmark> getApplicantsByGroupIdAndStatus(
            String groupID, Applicant.Status status) {
        Group group = groupDAO.getGroupBiId(groupID);
        Map<String, ApplicantBenchmark> groupApplicants = group.getApplicants();
        Map<String, ApplicantBenchmark> result = new HashMap<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : groupApplicants.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
