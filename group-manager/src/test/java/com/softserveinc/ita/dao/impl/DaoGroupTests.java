package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.DaoGroupBaseTest;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DaoGroupTests extends DaoGroupBaseTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testGetGroupByExistingStatusIdAndExpectCorrectResult() throws Exception {
        Group.Status status = Group.Status.BOARDING;
        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(new Group(Group.Status.BOARDING, "id3", new Course("Java", "pen-java.png"), "kv021"));
        expectedGroups.add(new Group(Group.Status.BOARDING, "id6", new Course("Java Script", "pen-net.png"), "kv061"));
        expectedGroups.add(new Group(Group.Status.BOARDING, "id9", new Course("Java", "pen-java.png"), "kv041"));
        expectedGroups.add(new Group(Group.Status.BOARDING, "id13", new Course("DevOps", "pen-devops.png"), "kv0753"));
        List<Group> realResult = groupDao.getGroupsByStatus(status.getName());
        assertEquals(realResult,expectedGroups);
    }
}
