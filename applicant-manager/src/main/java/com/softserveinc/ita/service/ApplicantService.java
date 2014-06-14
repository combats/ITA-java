package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;

public interface ApplicantService {

    Applicant editApplicant(Applicant applicant);

    Applicant addNewApplicant(Applicant applicant);
}
