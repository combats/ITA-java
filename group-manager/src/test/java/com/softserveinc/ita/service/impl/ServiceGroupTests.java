package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Applicant;
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
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
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
      public void testGetGroupsByCorrectStatusAndExpectCorrectList() throws Exception {
        ArrayList<Group> expectedList = new ArrayList<>();
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
}
