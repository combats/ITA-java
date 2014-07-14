package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;

import java.util.List;

public interface ApplicantSearchService {
    List<Applicant> getApplicantsByName(String wildcard);
    List<Applicant> getApplicantsByLastName(String wildcard);
}