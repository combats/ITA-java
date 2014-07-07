package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOHibernate implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserById(String userId) {
        return (User) sessionFactory.getCurrentSession().load(User.class, userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllUsersId() {
        List<String> allUsersId = new ArrayList<>();
        List<User> users = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
        for (User u : users) {
            allUsersId.add(u.getId());
        }
        return allUsersId;
    }

    @Override
    public String deleteUserById(String userId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(User.class, userId));
        return "";
    }

    @Override
    public String addUser(User user) {
        String userId = (String) sessionFactory.getCurrentSession().save(user);
        return userId;
    }

    @Override
    public String updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user.getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
