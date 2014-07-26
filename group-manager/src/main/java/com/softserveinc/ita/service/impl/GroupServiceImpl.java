package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.GroupDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDao;

    @Override
    public int getGroupCapacity(String groupID) {
        return groupDao.getGroupBiId(groupID).getCapacity();
    }

    @Override
    public Group updateApplicantsInGroup(String groupID,
                                         Map<String, ApplicantBenchmark> applicants) {
        Group group = groupDao.getGroupBiId(groupID);
        Map<String, ApplicantBenchmark> groupApplicants = group.getApplicants();
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            groupApplicants.put(entry.getKey(), entry.getValue());
        }
        return groupDao.updateGroup(group);
    }

    @Override
    public Map<String, ApplicantBenchmark> getApplicantsByGroupIdAndStatus(
            String groupID, Applicant.Status status) {
        Group group = groupDao.getGroupBiId(groupID);
        Map<String, ApplicantBenchmark> groupApplicants = group.getApplicants();
        Map<String, ApplicantBenchmark> result = new HashMap<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : groupApplicants.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
