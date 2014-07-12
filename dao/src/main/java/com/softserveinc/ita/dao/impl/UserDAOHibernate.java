package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.Role;
import com.softserveinc.ita.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<String> usersId = new ArrayList<String>();
        List<User> users = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        for (User user : users) {
            if (user != null) {
                usersId.add(user.getId());
            }
        }
        return usersId;
    }

    @Override
    public String deleteUserById(String userId) {
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("UserId", userId)).uniqueResult();
        sessionFactory.getCurrentSession().delete(user);
        return "";
    }

    @Override
    public String addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return "";
    }

    @Override
    public String updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return "";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public User findByName(String username) {

        return (User) sessionFactory.getCurrentSession().load(User.class, username);
    }

    /*public String activateUser(Integer id) {}*/
    /*public String disableUser(Integer id) {}*/

    public Role getSecurityRoleForUsername(String username) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, username);
        Role role = user.getUserRole();
        return role;
        }
}
