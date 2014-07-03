package com.softserveinc.ita.dao.impl;

import java.util.List;
import com.softserveinc.ita.dao.RoleDAO;
import com.softserveinc.ita.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOHibernate implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRole(Role securityRoleEntity) {
        try {
            sessionFactory.getCurrentSession().save(securityRoleEntity);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public List listRole() {
        return sessionFactory.getCurrentSession().createCriteria(Role.class).list();

    }
    @Override
    public Role getRoleById(Integer id) {
        return(Role)sessionFactory.getCurrentSession().load(Role.class, id);

    }
    @Override
    public void removeRole(Integer id) {
        Role securityRoleEntity = (Role) sessionFactory.getCurrentSession().load(Role.class, id);
        if (null != securityRoleEntity) {
            sessionFactory.getCurrentSession().delete(securityRoleEntity);
        }
    }
}