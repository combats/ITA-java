package service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by Anton on 04.06.2014.
 */
//Mock realization of business logic.
public class UserMockService implements UserService {

    @Autowired
    private JsonUtil jsonUtil;

    @Override
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
}
