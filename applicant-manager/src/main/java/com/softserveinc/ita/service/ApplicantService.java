package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApplicantService {
    List<Applicant> getApplicants();
}
