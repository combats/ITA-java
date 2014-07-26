package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import com.softserveinc.ita.exception.impl.GroupWithThisNameIsAlreadyExists;

import java.util.ArrayList;
import java.util.List;

public interface GroupService {
    List<Group> getGroupsByStatus(Group.Status groupStatus, long currentTime);

    List<Course> getCourses();

    Group createGroup(Group group);

    List<Group> getAllGroups();

    Group getGroupById(String id) throws GroupDoesntExistException;

    void removeGroup(String groupId) throws GroupDoesntExistException;

    Group updateGroup(Group group) throws GroupWithThisNameIsAlreadyExists;
}
