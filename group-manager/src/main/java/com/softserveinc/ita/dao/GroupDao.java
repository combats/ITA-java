package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getGroupsByStatus(String groupStatus);

    List<Applicant> getApplicantsByGroupID(String groupID);
}
