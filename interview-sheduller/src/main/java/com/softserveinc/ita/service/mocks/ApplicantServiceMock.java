package com.softserveinc.ita.service.mocks;

import com.softserveinc.ita.service.ApplicantService;

public class ApplicantServiceMock implements ApplicantService {
    public boolean applicantExists(String applicantId) {
        return applicantId.equals("testApplicantId");
    }
}