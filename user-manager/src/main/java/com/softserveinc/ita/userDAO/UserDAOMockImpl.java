package com.softserveinc.ita.userDAO;

import com.softserveinc.ita.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOMockImpl implements UserDAO {

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
