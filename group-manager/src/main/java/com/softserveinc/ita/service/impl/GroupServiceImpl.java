package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public Group getGroupById(String groupId) {
        return groupDAO.getGroupById(groupId);
    }
//
//    @Override
//    public Group addApplicants(String groupId, List<String> applicants, Applicant.Status status) {
//        Group group = getGroupById(groupId);
//        if (group == null) {
//            return null;
//        }
//        group.addOrUpdateApplicantIDListByStatus(applicants, status);
//        return group;
//    }
}
