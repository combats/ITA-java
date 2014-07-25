package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;

import java.util.ArrayList;
import java.util.List;

public interface GroupService {
    ArrayList<Group> getGroupsByStatus(Group.Status groupStatus);

    ArrayList<Course> getCourses();

    Group createGroup(Group group);

    ArrayList<Group> getAllGroups();

    List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException;
}
