package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import java.util.List;

import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.exception.GroupNotFoundException;

public interface ApplicantService {
    List<Applicant> getApplicants();
    List<Applicant> getApplicantsInAGroup(String groupID) throws GroupNotFoundException;
    Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException;

    Applicant addNewApplicant(Applicant applicant);

    Applicant editApplicant(Applicant applicant);
}
