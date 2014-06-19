package com.softserveinc.ita.service.mocks;

import com.softserveinc.ita.service.UserService;

public class UserServiceMock implements UserService {
    public boolean userExists(String userId){
        return userId.equals("testUserId");
    }
}