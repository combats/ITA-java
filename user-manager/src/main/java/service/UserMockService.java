package service;

import com.softserveinc.ita.entity.User;

import java.util.ArrayList;

/**
 * Created by Anton on 04.06.2014.
 */
//Mock DAO realization.
public class UserMockService implements UserService {

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
}
