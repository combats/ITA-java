package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;

import java.util.ArrayList;
import java.util.List;

public interface GroupDao {

    ArrayList<Group> getGroupsByStatus(String groupStatus);

    List<Applicant> getApplicantsByGroupID(String groupID);
}
