package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;

public interface UserDAO {
    User getUserByID (String UserID);
}
