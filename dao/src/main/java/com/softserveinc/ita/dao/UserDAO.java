package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Question;
import com.softserveinc.ita.entity.User;

import java.util.List;

public interface UserDAO {

    User getUserById(String userId);

    List<String> getAllUsersId();

    String deleteUserById(String userId);

    String addUser(User user);

    String updateUser(User user);

    List<User> getAllUsers();

}
