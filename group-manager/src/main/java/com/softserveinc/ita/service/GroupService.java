package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getGroupsByStatus(String groupStatus);

    List<Applicant> getApplicantsByGroupID(String groupID);
}
