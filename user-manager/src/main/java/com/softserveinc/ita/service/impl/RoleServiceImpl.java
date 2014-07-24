package com.softserveinc.ita.service.impl;


import com.softserveinc.ita.dao.RoleDAO;
import com.softserveinc.ita.entity.Role;
import com.softserveinc.ita.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
