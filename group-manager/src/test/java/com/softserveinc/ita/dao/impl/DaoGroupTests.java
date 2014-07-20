package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.DaoGroupBaseTest;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DaoGroupTests extends DaoGroupBaseTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testGetGroupByExistingStatusIdAndExpectCorrectResult() throws Exception {
        Group.Status status = Group.Status.BOARDING;
        int expectedGroupsNumber = 4;
        ArrayList<Group> realResult = groupDao.getGroupsByStatus(status.getName());
        assertEquals(realResult.size(), expectedGroupsNumber);
    }

    @Test
    public void testAddNewGroupAndExpectCorrectNewGroup() {
        Group group = new Group(Group.Status.IN_PROCESS, "id43", new Course("Java", "pen-java.png"), "kv321");
        Group newGroup = groupDao.addGroup(group);
        assertEquals(group.getGroupID(), newGroup.getGroupID());
    }
}
