package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.WrongGroupStatusException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public ArrayList<Group> getGroupsByStatus(Group.Status groupStatus) {
        if(isWrongStatus(groupStatus)){
            throw new WrongGroupStatusException();
        }
        return groupDao.getGroupsByStatus(groupStatus);
    }

    public ArrayList<Group> getGroupsByStatusDate(Group.Status groupStatus) {
        ArrayList<Group> groups = groupDao.getAllGroups();
        switch(groupStatus){
            case PLANNED :
                break;
            case BOARDING:
                break;
            case IN_PROCESS:
                break;
            case FINISHED:
                break;

        }

        return null;
    }

    @Override
    public ArrayList<Course> getCourses() {
        return groupDao.getCourses();
    }

    @Override
    public Group createGroup(Group group) {
        return groupDao.addGroup(group);
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    private boolean isWrongStatus(Group.Status groupStatus) {
        for(Group.Status status : Group.Status.values()){
            if (status.equals(groupStatus)) {
                return false;
            }
        }
        return true;
    }
}
