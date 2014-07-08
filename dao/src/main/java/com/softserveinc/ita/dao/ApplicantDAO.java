package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;

import java.util.List;

/**
 * Created by mskryntc on 23.06.2014.
 */
public interface ApplicantDAO {

    List<Applicant> getApplicants();

    Applicant getApplicantById(String applicantId);

    Applicant addNewApplicant(Applicant applicant);

    Applicant editApplicant(Applicant applicant);
}
