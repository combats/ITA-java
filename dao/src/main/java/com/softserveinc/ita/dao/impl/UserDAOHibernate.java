package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.Question;
import com.softserveinc.ita.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return (List<String>) sessionFactory.getCurrentSession().createCriteria(User.class)
                .setProjection(Projections.projectionList().add(Projections.property("id"))).list();
    }

    @Override
    public String deleteUserById(String userId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(User.class, userId));
        return userId;
    }

    @Override
    public String addUser(User user) {
        return (String) sessionFactory.getCurrentSession().save(user);
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
