package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;

public interface ApplicantService {

    Applicant addNewApplicant(Applicant applicant);

    Applicant getApplicantById(String applicantId);
}
