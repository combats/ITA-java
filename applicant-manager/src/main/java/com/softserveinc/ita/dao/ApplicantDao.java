package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;

import java.util.List;

public interface ApplicantDao {
    List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException;
}
