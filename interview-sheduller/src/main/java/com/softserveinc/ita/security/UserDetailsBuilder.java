package com.softserveinc.ita.security;

import com.softserveinc.ita.entity.Role;
import com.softserveinc.ita.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsBuilder {

    org.springframework.security.core.userdetails.User buildUserSecuredFromUser(User user) {

        String username = user.getName();

        String password = user.getPassword();

        boolean enabled = user.getActive();

        boolean accountNonExpired = user.getActive();

        boolean credentialsNonExpired = user.getActive();

        boolean accountNonLocked = user.getActive();

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : user.getSecurityRoleCollection()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
         return new org.springframework.security.core.userdetails.User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}



