package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;

import java.util.List;

public interface GroupService {
    public Group getGroupById(String groupId);

//    public Group addApplicants(String groupId, List<String> applicants, Applicant.Status status);
}
