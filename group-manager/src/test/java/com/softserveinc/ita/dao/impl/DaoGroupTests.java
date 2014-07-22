package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.DaoGroupBaseTest;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DaoGroupTests extends DaoGroupBaseTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testAddNewGroupAndExpectCorrectNewGroup() {
        Group group = new Group("id43", new Course("Java", "pen-java.png"), "kv321");
        Group newGroup = groupDao.addGroup(group);
        assertEquals(group.getGroupID(), newGroup.getGroupID());
    }
    @Test(expected = GroupDoesntExistException.class)
    public void getApplicantsByNotExistingIdAndExpectException() throws GroupDoesntExistException {
        groupDao.getApplicantsByGroupID("wrong");
    }
}
