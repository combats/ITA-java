package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;

import java.util.Map;

public interface GroupService {
    int getGroupCapacity(String groupID);

    Group updateApplicantsInGroup(String groupID, Map<String,
            ApplicantBenchmark> applicants);

    Map<String, ApplicantBenchmark> getApplicantsByGroupIdAndStatus(
            String groupID, Applicant.Status status);
}
