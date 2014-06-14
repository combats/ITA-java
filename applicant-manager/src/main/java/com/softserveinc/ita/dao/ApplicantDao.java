package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;

public interface ApplicantDao {

    Applicant editApplicant(Applicant applicant);

    Applicant addNewApplicant(Applicant applicant);
}
