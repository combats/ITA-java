package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.dao.impl.GroupDaoMockImpl;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import com.softserveinc.ita.service.GroupService;
import com.softserveinc.ita.service.ServiceGroupBaseTest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        ArrayList<Group> groupList = new ArrayList<Group>();
        //Planned
        Group group1 = new Group("id1", new Course("Java", "pen-java.png"), "kv001");
        group1 = GroupDaoMockImpl.setTime(group1, new DateTime(2014, 8, 1, 0, 0, 0).getMillis(),
                new DateTime(2014, 8, 10, 0, 0, 0).getMillis(), new DateTime(2014, 8, 20, 0, 0, 0).getMillis());
        groupList.add(group1);
        expectedList.add(group1);
        Group group2 = new Group("id3", new Course("Java", "pen-java.png"), "kv021");
        group2 = GroupDaoMockImpl.setTime(group2, new DateTime(2014, 8, 1, 0, 0, 0).getMillis(),
                new DateTime(2014, 8, 10, 0, 0, 0).getMillis(), new DateTime(2014, 8, 20, 0, 0, 0).getMillis());
        groupList.add(group2);
        expectedList.add(group2);
        //Boarding
        Group group3 = new Group("id4", new Course("Sharp", "pen-net.png"), "kv012");
        group3 = GroupDaoMockImpl.setTime(group3, new DateTime(2014, 7, 17, 0, 0, 0).getMillis(),
                new DateTime(2014, 8, 10, 0, 0, 0).getMillis(), new DateTime(2014, 8, 20, 0, 0, 0).getMillis());
        groupList.add(group3);

        when(groupDao.getAllGroups()).thenReturn(groupList);
        ArrayList<Group> groupsByStatus = groupService.getGroupsByStatus(Group.Status.PLANNED);
        assertEquals(groupsByStatus,expectedList);
    }

    @Test
    public void testAddNewGroupAndExpectIsOk(){
        Group group = new Group();
        when(groupDao.addGroup(group)).thenReturn(new Group("id100"));
        assertEquals("id100", groupService.createGroup(group).getGroupID());
    }

    @Test(expected = GroupDoesntExistException.class)
    public void getApplicantsByNotExistingIdAndExpectException() throws GroupDoesntExistException {
        when(groupDao.getApplicantsByGroupID("wrong")).thenThrow(GroupDoesntExistException.class);
        groupService.getApplicantsByGroupID("wrong");
        verify(groupDao, atLeastOnce()).getApplicantsByGroupID("wrong");
    }

    @Test
    public void getAllGroupsAndExpectCorrectList(){
        ArrayList<Group> expectedList = new ArrayList<Group>();
        expectedList.add(new Group("id3", new Course("Java", "pen-java.png"), "kv021"));
        expectedList.add(new Group("id6", new Course("Java Script", "pen-net.png"), "kv061"));
        expectedList.add(new Group("id9", new Course("Java", "pen-java.png"), "kv041"));
        when(groupDao.getAllGroups()).thenReturn(expectedList);
        assertEquals(expectedList, groupService.getAllGroups());
        verify(groupDao, atLeastOnce()).getAllGroups();
    }

    @Test
    public void testGetApplicantsByGroupIDAndExpectValidList() throws Exception {
        List<Applicant> expected = new ArrayList<>();
        Applicant applicantOne = new Applicant("TestApplicantOneName", "TestApplicantOneSurname");
        Applicant applicantTwo = new Applicant("TestApplicantTwoName", "TestApplicantTwoSurname");
        Applicant applicantThree = new Applicant("TestApplicantThreeName", "TestApplicantThreeSurname");
        Collections.addAll(expected, applicantOne, applicantTwo, applicantThree);
        when(groupDao.getApplicantsByGroupID("TestGroupID")).thenReturn(expected);

        List<Applicant> actual = groupService.getApplicantsByGroupID("TestGroupID");
        assertEquals(expected, actual);
        verify(groupDao, atLeastOnce()).getApplicantsByGroupID("TestGroupID");
    }

    @Test
    public void testGetGroupByExistingIdAndExpectCorrectGroup() throws Exception{
        Group group = new Group("id1");
        when(groupDao.getGroupById("id1")).thenReturn(group);
        assertEquals(group,groupService.getGroupById("id1"));
    }

    @Test(expected = GroupDoesntExistException.class)
    public void testGetGroupByNotExistingIdAndExpectException()throws Exception{
        when(groupDao.getGroupById("notExistingId")).thenThrow(GroupDoesntExistException.class);
        groupService.getGroupById("notExistingId");
    }
}
