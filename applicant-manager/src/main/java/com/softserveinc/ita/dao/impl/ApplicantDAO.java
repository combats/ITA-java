package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;

import java.util.List;

public interface ApplicantDAO {

    List<Applicant> getApplicants();

    Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException;

    Applicant addNewApplicant(Applicant applicant);

    Applicant editApplicant(Applicant applicant);

}
