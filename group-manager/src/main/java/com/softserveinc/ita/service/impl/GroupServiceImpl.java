package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.CourseDao;
import com.softserveinc.ita.dao.GroupDAO;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import com.softserveinc.ita.exception.impl.GroupWithThisNameIsAlreadyExists;
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

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Group> getGroupsByStatus(Group.Status groupStatus, long currentTime) {
        List<Group> groups = groupDao.getAllGroups();
        ArrayList<Group> choosenGroups = new ArrayList<>();
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
        List<Course> courses = courseDao.getAllCourses();
        return courses;
    }

    @Override
    public Group createGroup(Group group) {
        return groupDao.addGroup(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public Group getGroupById(String id) throws GroupDoesntExistException {
        Group searchedGroup = groupDao.getGroupById(id);
        if (searchedGroup == null) {
            throw new GroupDoesntExistException();
        }
        return searchedGroup;
    }

    @Override
    public void removeGroup(String groupId) throws GroupDoesntExistException {
        try {
            groupDao.removeGroup(groupId);
        } catch (Exception e) {
            throw new GroupDoesntExistException();
        }
    }

    @Override
    public Group updateGroup(Group group) throws GroupWithThisNameIsAlreadyExists {
        try {
            Group updatedGroup = groupDao.updateGroup(group);
            return updatedGroup;
        } catch (Exception e) {
            throw new GroupWithThisNameIsAlreadyExists();
        }
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
