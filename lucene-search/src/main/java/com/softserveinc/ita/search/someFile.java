package com.softserveinc.ita.search;

import com.softserveinc.ita.entity.Applicant;

import java.util.List;

public interface someFile {

    List<Applicant> getApplicants();

    Applicant getApplicantById(String applicantId);

    Applicant addNewApplicant(Applicant applicant);

    Applicant editApplicant(Applicant applicant);
}
