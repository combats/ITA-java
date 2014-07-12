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

        boolean enabled = user.isActive();

        boolean accountNonExpired = user.isActive();

        boolean credentialsNonExpired = user.isActive();

        boolean accountNonLocked = user.isActive();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getRoleName()));

         return new org.springframework.security.core.userdetails.User(username, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}



