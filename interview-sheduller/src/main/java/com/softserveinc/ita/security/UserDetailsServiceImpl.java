package com.softserveinc.ita.security;

import com.softserveinc.ita.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserDetailsBuilder userDetailsBuilder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        com.softserveinc.ita.entity.User user = userDAO.findByName(username);
            if (user == null) throw new UsernameNotFoundException("User not found");

        return userDetailsBuilder.buildUserSecuredFromUser(user);
    }
}