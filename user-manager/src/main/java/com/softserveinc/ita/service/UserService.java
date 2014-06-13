package com.softserveinc.ita.service;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException;
}
