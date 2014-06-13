package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;

public interface ApplicantService {
     Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException;
}
