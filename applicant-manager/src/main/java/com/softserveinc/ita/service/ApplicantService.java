package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;

import java.util.List;


public interface ApplicantService {
    List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException;
}
