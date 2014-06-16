package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;

public interface ApplicantDAO {
     Applicant getApplicantById(String applicantId) throws ApplicantDoesNotExistException;
}
