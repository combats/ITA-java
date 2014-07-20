package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.WrongGroupStatusException;
import com.softserveinc.ita.service.GroupService;
import com.softserveinc.ita.service.ServiceGroupBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServiceGroupTests extends ServiceGroupBaseTest {

    @Autowired
    @InjectMocks
    private GroupService groupService;

    @Autowired
    @Mock
    private GroupDao groupDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
      public void testGetGroupsByCorrectStatusAndExpectCorrectList() throws Exception{
        ArrayList<Group> expectedList = new ArrayList<Group>();
        expectedList.add(new Group(Group.Status.BOARDING, "id3", new Course("Java", "pen-java.png"), "kv021"));
        expectedList.add(new Group(Group.Status.BOARDING, "id6", new Course("Java Script", "pen-net.png"), "kv061"));
        expectedList.add(new Group(Group.Status.BOARDING, "id9", new Course("Java", "pen-java.png"), "kv041"));
        when(groupDao.getGroupsByStatus(Group.Status.BOARDING.getName())).thenReturn(expectedList);
        assertEquals(expectedList, groupService.getGroupsByStatus(Group.Status.BOARDING.getName()));
        verify(groupDao, atLeastOnce()).getGroupsByStatus(Group.Status.BOARDING.getName());
    }

    @Test(expected=WrongGroupStatusException.class)
    public void testGetGroupByWrongStatusAndExpectException() throws Exception{
        groupService.getGroupsByStatus("wrongStatus");
    }

    @Test
    public void testAddNewGroupAndExpectIsOk(){
        Group group = new Group();
        when(groupDao.addGroup(group)).thenReturn(new Group("id100"));
        assertEquals("id100",groupService.createGroup(group).getGroupID());
    }

    @Test
    public void getAllGroupsAndExpectIsOk(){
        ArrayList<Group> expectedList = new ArrayList<Group>();
        expectedList.add(new Group(Group.Status.BOARDING, "id3", new Course("Java", "pen-java.png"), "kv021"));
        expectedList.add(new Group(Group.Status.BOARDING, "id6", new Course("Java Script", "pen-net.png"), "kv061"));
        expectedList.add(new Group(Group.Status.BOARDING, "id9", new Course("Java", "pen-java.png"), "kv041"));
        when(groupDao.getAllGroups()).thenReturn(expectedList);
        assertEquals(expectedList, groupService.getAllGroups());
        verify(groupDao, atLeastOnce()).getAllGroups();
    }

}
