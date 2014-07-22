package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.GroupDAO;
import com.softserveinc.ita.dao.GroupDao;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDao {
    Group group = new Group("31", "KV-004", 2);

    {
        List<String> newApps = new ArrayList<>();
        newApps.add("1");
        newApps.add("2");
        List<String> scheduled = new ArrayList<>();
        scheduled.add("3");
        scheduled.add("4");
        List<String> passed = new ArrayList<>();
        passed.add("5");
        passed.add("6");
        List<String> notPassed = new ArrayList<>();
        notPassed.add("7");
        List<String> employed = new ArrayList<>();
        employed.add("8");
        group.addOrUpdateApplicantIDListByStatus(newApps, new ApplicantBenchmark(Applicant.Status.NOT_SCHEDULED, -1));
        group.addOrUpdateApplicantIDListByStatus(scheduled, new ApplicantBenchmark(Applicant.Status.SCHEDULED, -1));
        group.addOrUpdateApplicantIDListByStatus(passed, new ApplicantBenchmark(Applicant.Status.PASSED, -1));
        group.addOrUpdateApplicantIDListByStatus(notPassed, new ApplicantBenchmark(Applicant.Status.NOT_PASSED, -1));
        group.addOrUpdateApplicantIDListByStatus(employed, new ApplicantBenchmark(Applicant.Status.EMPLOYED, -1));
    }

//    @Override
//    public Group getGroupById(String groupId) {
//        return group;
//    }

    @Override
    public List<Group> getGroupsByStatus(String groupStatus) {
        return null;
    }

    @Override
    public List<Applicant> getApplicantsByGroupID(String groupID) {
        return null;
    }
}
