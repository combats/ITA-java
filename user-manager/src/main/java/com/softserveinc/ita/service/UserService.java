package com.softserveinc.ita.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void deleteUser(String userID) throws Exception;
}
