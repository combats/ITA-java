package com.softserveinc.ita.DAO;

import com.softserveinc.ita.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("userDAOImpl")
public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Collections.addAll(users, new User("id3"), new User("idY"), new User("id09z"));
        return users;
    }
}
