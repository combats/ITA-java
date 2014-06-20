package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
    public User getUserByID(String UserID) {
        User user = null;
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
        }};

        for (User u : users) {
            if (u.getUserID().equals(UserID)) {
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

    @Override
    public void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException {
        if(dbOfUsers.containsKey(userID)){
            dbOfUsers.remove(userID);
        } else {
            String errorMessage = "User DAOMock: User with userID not found: ";
            throw new UserIDNotFoundUserDaoMockException(errorMessage + userID);
        }
    }

    @Override
    public User changeUser(User changingUser){
        if(changingUser==null){
            return null;
        }
        List<User> users = new ArrayList<User>(){
            {
                add(new User("id1"));
                add(new User("id2"));
                add(new User("id3"));
            }
        };
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(changingUser.getUserID())) {
                users.set(i, changingUser);
                return users.get(i);
            }
        }
        return null;
    }
}
