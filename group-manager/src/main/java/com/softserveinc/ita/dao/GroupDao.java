package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;

import java.util.ArrayList;
import java.util.List;

public interface GroupDao {

    Group addGroup(Group group);

    ArrayList<Group> getAllGroups();

    Group getGroupById(String id);

    void removeGroup(String groupId);

    Group updateGroup(Group group);
}
