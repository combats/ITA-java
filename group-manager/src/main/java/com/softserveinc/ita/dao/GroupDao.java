package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;

import java.util.ArrayList;
import java.util.List;

public interface GroupDao {

    ArrayList<Course> getCourses();

    Group addGroup(Group group);

    ArrayList<Group> getAllGroups();

    List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException;
}
