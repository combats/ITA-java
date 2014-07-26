package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.DaoGroupBaseTest;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class DaoGroupTests extends DaoGroupBaseTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testAddNewGroupAndExpectCorrectNewGroup() {
        Group group = new Group("id43", new Course("Java", "pen-java.png"), "kv321");
        Group newGroup = groupDao.addGroup(group);
        assertEquals(group.getGroupID(), newGroup.getGroupID());
    }

    @Test
    public void testAddNewGroupandExpectGroupWithNewId(){
        Group group = new Group("id1");
        Course groupCourse = new Course("Java");
        group.setCourse(groupCourse);
        group =  groupDao.addGroup(group);
        assertEquals(group.getGroupID(),"id100");
        assertEquals(group.getCourse().getImageRef(),"pen-java.png");
    }

    @Test
    public void testGetGroupByExistingIdAndExpectCorrectGroup() {
        Group expectedGroup = new Group("id1", new Course("Java", "pen-java.png"), "kv001");
        expectedGroup.setStartBoardingTime( new DateTime(2014, 8, 1, 0, 0, 0).getMillis());
        expectedGroup.setStartTime(new DateTime(2014, 8, 10, 0, 0, 0).getMillis());
        expectedGroup.setEndTime(new DateTime(2014, 8, 20, 0, 0, 0).getMillis());
        Group receivedGroup = groupDao.getGroupById(expectedGroup.getGroupID());
        assertEquals(expectedGroup,receivedGroup);
    }

    @Test
    public void testUpdateGroupByExistingId() {
        Group group = new Group("id1", new Course("Java", "pen-java.png"), "kv002");
        group.setStartBoardingTime(100);
        group.setStartTime(200);
        group.setEndTime(300);
        Group updatedGroup = groupDao.updateGroup(group);
        assertEquals(group, updatedGroup);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateGroupByNotExistingId() {
        Group group = new Group("id111", new Course("Java", "pen-java.png"), "kv002");
        group.setStartBoardingTime(100);
        group.setStartTime(200);
        group.setEndTime(300);
        Group updatedGroup = groupDao.updateGroup(group);
    }
}
