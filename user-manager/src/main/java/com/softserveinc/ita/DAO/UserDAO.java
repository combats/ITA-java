package com.softserveinc.ita.DAO;

import com.softserveinc.ita.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
}
