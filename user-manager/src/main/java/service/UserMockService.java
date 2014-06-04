package service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by Anton on 04.06.2014.
 */
//Mock DAO realization.
public class UserMockService implements UserService {

    @Autowired
    private JsonUtil jsonUtil;

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
