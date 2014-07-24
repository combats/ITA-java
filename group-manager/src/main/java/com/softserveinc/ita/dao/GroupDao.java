package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;

import java.util.List;

public interface GroupDao {

    List<Course> getCourses();

    Group getGroupById(String groupId);

    Group addGroup(Group group);

    List<Group> getAllGroups();

    List<Applicant> getApplicantsByGroupID(String groupID) throws GroupDoesntExistException;
}
