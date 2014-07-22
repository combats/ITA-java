package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import java.util.ArrayList;
import java.util.List;

public interface GroupService {

    ArrayList<Group> getGroupsByStatus(String groupStatus);

    List<Applicant> getApplicantsByGroupID(String groupID);
}
