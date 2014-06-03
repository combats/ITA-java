package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;

public interface ApplicantDao {

    Applicant addNewApplicant(Applicant applicant);

    Applicant getApplicantById(String applicantId);
}
