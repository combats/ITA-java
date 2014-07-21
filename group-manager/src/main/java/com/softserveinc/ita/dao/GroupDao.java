package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import java.util.ArrayList;

public interface GroupDao {

    ArrayList<Course> getCourses();

    Group addGroup(Group group);

    ArrayList<Group> getAllGroups();
}
