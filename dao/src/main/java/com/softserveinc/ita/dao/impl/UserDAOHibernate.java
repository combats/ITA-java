package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import org.hibernate.Criteria;
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
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria( User.class );
        criteria = criteria.add( Restrictions.eq("email", email));
        Object obj = criteria.uniqueResult();
//        return (User) sessionFactory.getCurrentSession().createCriteria( User.class ).
//                add( Restrictions.eq("email", email) ).
//                uniqueResult();
        User user = (User) obj;
        return user;
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
    public User addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        String userId = (String) session.save(user);
        return (User) session.load(User.class, userId);
    }

    @Override
    public User updateUser(User user) {
        String userId = user.getId();
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return (User) session.load(User.class, userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
