package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import java.util.ArrayList;

public interface GroupService {
    ArrayList<Group> getGroupsByStatus(Group.Status groupStatus);

    ArrayList<Course> getCourses();

    Group createGroup(Group group);

    ArrayList<Group> getAllGroups();
}
