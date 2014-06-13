package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.service.HttpRestService;

public class HttpRestServiceMock implements HttpRestService{
    public boolean userExists(String userId){
        return userId.equals("testUserId");
    }
    public boolean applicantExists(String applicantId){
        return applicantId.equals("testApplicantId");
    }
}
