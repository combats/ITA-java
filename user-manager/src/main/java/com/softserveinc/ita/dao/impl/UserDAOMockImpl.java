package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.Role;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import org.springframework.stereotype.Repository;
import com.softserveinc.ita.exception.UserAlreadyExistsException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDAOMockImpl implements UserDAO {

    private AtomicInteger idAutoGeneration = new AtomicInteger();
    private Hashtable<String, User> dbDaoMock = new Hashtable<String, User>();

    public UserDAOMockImpl() {

        String id1 = String.valueOf(idAutoGeneration.incrementAndGet());
        User u1 = new User(id1, "John", "Doe", new Role("1","Admin"), "123456", "j@gmail.com", "095-555-55-55");
        dbDaoMock.put(id1, u1);

        String id2 = String.valueOf(idAutoGeneration.incrementAndGet());
        User u2 = new User(id2, "Victoria", "Ashworth", new Role("2","HR"), "123456", "va@gmail.com", "095-699-98-05");
        dbDaoMock.put(id2, u2);

        String id3 = String.valueOf(idAutoGeneration.incrementAndGet());
        User u3 = new User(id3, "Francisco", "Chang", new Role("2","HR"), "123456", "fc@gmail.com", "050-888-55-55");
        dbDaoMock.put(id3, u3);

        String id4 = String.valueOf(idAutoGeneration.incrementAndGet());
        User u4 = new User(id4, "Paolo", "Accorti", new Role("1","Admin"), "123456", "paoloA@gmail.com", "091-000-11-55");
        dbDaoMock.put(id4, u4);

        String id5 = String.valueOf(idAutoGeneration.incrementAndGet());
        User u5 = new User(id5, "Fran", "Wilson", new Role("2","HR"), "123456", "fran@gmail.com", "093-700-55-30");
        dbDaoMock.put(id5, u5);

    }

    @Override
    public List<User> getUsers() {
        return new ArrayList(dbDaoMock.values());
    }

    @Override
    public User getUserByID(String UserID) {
        return dbDaoMock.get(UserID);
    }

    @Override
    public ArrayList<String> getAllUsersID() {
        return new ArrayList(dbDaoMock.keySet());
    }

    public User postNewUser(User user) throws UserAlreadyExistsException{
        if (dbDaoMock.containsValue(user)) {
            throw new UserAlreadyExistsException();
        }

        String id1 = String.valueOf(idAutoGeneration.incrementAndGet());
        user.setId(id1);
        dbDaoMock.put(id1, user);
        return user;
    }

    @Override
    public String deleteUser(String userID) throws UserDoesNotExistException {
        if(!dbDaoMock.containsKey(userID)){
            throw new UserDoesNotExistException();
        }
        dbDaoMock.remove(userID);
        return userID;
    }

    @Override
    public User changeUser(User changingUser){
        dbDaoMock.put(changingUser.getId(), changingUser);
        return changingUser;
    }
}
