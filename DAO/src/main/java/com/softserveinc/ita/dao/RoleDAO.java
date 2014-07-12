package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Role;

import java.util.List;

public interface RoleDAO {

    void addRole(Role securityRoleEntity);

    List listRole();

    Role getRoleById(Integer id);

    void removeRole(Integer id);

}
