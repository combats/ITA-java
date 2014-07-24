package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class GroupDAOTests extends BaseDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GroupDAO groupDAO;

    static private Course courseOne;
    static private Course courseTwo;
    static private Course courseThree;
    static private Group groupOne;
    static private Group groupTwo;
    static private Group groupThree;

    @BeforeClass
    static public void setUpOnce() {
        courseOne = new Course("TestCourseOne", "TestRefOne");
        courseTwo = new Course("TestCourseTwo", "TestRefTwo");
        courseThree = new Course("TestCourseThree", "TestRefThree");
        groupOne = new Group(courseOne, "TestGroupOne");
        groupTwo = new Group(courseTwo, "TestGroupOne");
        groupThree = new Group(courseThree, "TestGroupOne");
    }

    @Test
    public void testAddGroup() {
        sessionFactory.getCurrentSession().save(courseOne);
        Group actual = groupDAO.addGroup(groupOne);
        assertEquals(groupOne, actual);
    }

    @Test
    public void testGetAllGroups() {
        Session session = sessionFactory.getCurrentSession();
        session.save(courseOne);
        session.save(courseTwo);
        session.save(courseThree);
        session.save(groupOne);
        session.save(groupTwo);
        session.save(groupThree);
        List<Group> allGroups = groupDAO.getAllGroups();
        assertEquals(3, allGroups.size());
    }

    @Test
    public void testGetGroupById() {
        Session session = sessionFactory.getCurrentSession();
        Course course = new Course("TestCourse");
        Group expected = new Group(course, "TestGroupName");
        session.save(course);
        String groupId = (String) session.save(expected);
        Group actual = groupDAO.getGroupBiId(groupId);
        assertEquals(expected, actual);
    }
}
