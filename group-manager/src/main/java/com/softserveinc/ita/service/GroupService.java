package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {
    public Group getGroupById(String groupId);

    List<Group> getGroupsByStatus(String groupStatus);

    List<Applicant> getApplicantsByGroupID(String groupID);

    int getGroupCapacity(String groupId);

    Map<String, ApplicantBenchmark> getApplicantsByStatus(String groupId, Applicant.Status status);

    void addOrUpdateApplicantIDListByStatus(String groupId, Map<String, ApplicantBenchmark> applicants);
}
