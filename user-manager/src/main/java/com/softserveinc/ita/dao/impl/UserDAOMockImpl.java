package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAOMockImpl implements UserDAO {
    private Map<String, User> dbOfUsers;

    public UserDAOMockImpl() {
        dbOfUsers = new HashMap<>();
        dbOfUsers.put("121", new User("121"));
        dbOfUsers.put("122", new User("122"));
        dbOfUsers.put("123", new User("123"));
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Collections.addAll(users, new User("1", "Pupkin", "Vasiliy"),
                new User("2", "Ivanov", "Ivan"),
                new User("3", "Fedorov", "Fedor"));
        return users;
    }

    @Override
    public User getUserByID(String UserID) {
        User user = null;
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
        }};

        for (User u : users) {
            if (u.getID().equals(UserID)) {
                user = u;
            }
        }
        return user;
    }

    @Override
    public ArrayList<String> getAllUsersID() {
        ArrayList<String> usersID = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        return usersID;
    }

    public User postNewUser(User user) throws UserAlreadyExistsException {
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
        }};
        for (User u : users) {
            if (user.equals(u))
                throw new UserAlreadyExistsException();
        }
        return user;
    }

    @Override
    public String deleteUser(String userID) throws UserDoesNotExistException {
        if (!dbOfUsers.containsKey(userID)) {
            throw new UserDoesNotExistException();
        }
        dbOfUsers.remove(userID);
        return userID;
    }

    @Override
    public User changeUser(User changingUser) {
        if (changingUser == null) {
            return null;
        }
        List<User> users = new ArrayList<User>() {
            {
                add(new User("id1"));
                add(new User("id2"));
                add(new User("id3"));
            }
        };
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(changingUser.getID())) {
                users.set(i, changingUser);
                return users.get(i);
            }
        }
        return null;
    }
}
