package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;

import java.util.List;

public interface ApplicantService {
    List<Applicant> getApplicants();

    List<Applicant> getApplicantsByGroupID(String groupID);

    Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException;

    Applicant addNewApplicant(Applicant applicant);

    Applicant editApplicant(Applicant applicant);

    List<String> getApplicantIDList();
}
