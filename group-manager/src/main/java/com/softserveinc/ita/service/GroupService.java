package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;

import java.util.List;
import java.util.Map;

public interface GroupService {
    List<Group> getGroupsByStatus(Group.Status groupStatus);

    List<Course> getCourses();

    Group createGroup(Group group);

    List<Group> getAllGroups();

    List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException;

    int getGroupCapacity(String groupID);

    Group updateApplicantsInGroup(String groupID, Map<String,
            ApplicantBenchmark> applicants);

    Map<String, ApplicantBenchmark> getApplicantsByGroupIdAndStatus(
            String groupID, Applicant.Status status);
}
