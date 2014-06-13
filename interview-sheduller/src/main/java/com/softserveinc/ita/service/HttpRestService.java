package com.softserveinc.ita.service;

public interface HttpRestService {
    public boolean userExists(String userId);
    public boolean applicantExists(String applicantId);
}
