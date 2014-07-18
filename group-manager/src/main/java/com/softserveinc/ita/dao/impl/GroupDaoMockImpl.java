package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class GroupDaoMockImpl implements GroupDao {
    private ArrayList<Group> groups = new ArrayList<Group>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    public GroupDaoMockImpl() {
        groups.add(new Group(Group.Status.IN_PROCESS, "id1", new Course("Java", "pen-java.png"), "kv001"));
        groups.add(new Group(Group.Status.OFFERING, "id2", new Course("Sharp", "pen-net.png"), "kv041"));
        groups.add(new Group(Group.Status.BOARDING, "id3", new Course("Java", "pen-java.png"), "kv021"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id4", new Course("Sharp", "pen-net.png"), "kv012"));
        groups.add(new Group(Group.Status.OFFERING, "id5", new Course("Java Script", "pen-net.png"), "kv054"));
        groups.add(new Group(Group.Status.BOARDING, "id6", new Course("Java Script", "pen-net.png"), "kv061"));
        groups.add(new Group(Group.Status.OFFERING, "id7", new Course("DevOps", "pen-devops.png"), "kv068"));
        groups.add(new Group(Group.Status.OFFERING, "id8", new Course("DevOps", "pen-devops.png"), "kv075"));
        groups.add(new Group(Group.Status.BOARDING, "id9", new Course("Java", "pen-java.png"), "kv041"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id10", new Course("Sharp", "pen-net.png"), "kv064"));
        groups.add(new Group(Group.Status.OFFERING, "id11", new Course("Java Script", "pen-net.png"), "kv123"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id12", new Course("Java Script", "pen-net.png"), "kv532"));
        groups.add(new Group(Group.Status.BOARDING, "id13", new Course("DevOps", "pen-devops.png"), "kv0753"));
        groups.add(new Group(Group.Status.OFFERING, "id14", new Course("DevOps", "pen-devops.png"), "kv112"));
        groups.add(new Group(Group.Status.FINISHED, "id15", new Course("Java Script", "pen-net.png"), "kv532"));
        groups.add(new Group(Group.Status.FINISHED, "id16", new Course("DevOps", "pen-devops.png"), "kv0753"));
        groups.add(new Group(Group.Status.FINISHED, "id17", new Course("DevOps", "pen-devops.png"), "kv112"));
    }

    @Override
    public ArrayList<Group> getGroupsByStatus(String groupStatus) {
        ArrayList<Group> chosenByStatusGroups = new ArrayList<Group>();
        for (Group group : groups) {
            if (groupStatus.equals(group.getGroupStatus().getName())) {
                chosenByStatusGroups.add(group);
            }
        }
        return chosenByStatusGroups;
    }

    @Override
    public ArrayList<Course> getCourses() {
        courses.clear();
        courses.add(new Course("DevOps", "pen-devops.png"));
        courses.add(new Course("Java Script", "pen-net.png"));
        courses.add(new Course("Java", "pen-java.png"));
        courses.add(new Course("Sharp", "pen-net.png"));
        return courses;
    }

    @Override
    public Group addGroup(Group group) {
//        groups.add(group);
//        group.setGroupID("id100");
        return group;
    }
}