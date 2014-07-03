package com.softserveinc.ita.security;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates how to use a {@link org.springframework.test.web.servlet.request.RequestPostProcessor} to add
 * request-building methods for establishing a security context for Spring Security.
 * While these are just examples, * <a href="https://jira.springsource.org/browse/SEC-2015">official support</a>
 * for Spring Security is planned.
 */
final class SecurityRequestPostProcessors {

    /**
     * Establish a security context for a user with the specified username. All
     * details are declarative and do not require that the user actually exists.
     * This means that the authorities or roles need to be specified too.
     */
    public static UserRequestPostProcessor user(String username) {
        return new UserRequestPostProcessor(username);
    }

    /**
     * Establish a security context for a user with the specified username. The
     * additional details are obtained from the {@link org.springframework.security.core.userdetails.UserDetailsService} declared in the {@link org.springframework.web.context.WebApplicationContext}.
     */
    public static UserDetailsRequestPostProcessor userDetailsService(String username) {
        return new UserDetailsRequestPostProcessor(username);
    }

    /**
     * Establish a security context with the given {@link org.springframework.security.core.context.SecurityContext} and  thus be authenticated
     * with {@link org.springframework.security.core.context.SecurityContext#getAuthentication()}.
     */
    public SecurityContextRequestPostProcessor securityContext(SecurityContext securityContext) {
        return new SecurityContextRequestPostProcessor(securityContext);
    }


    /** Support class for {@link org.springframework.test.web.servlet.request.RequestPostProcessor}'s that establish a Spring Security context */
    private static abstract class SecurityContextRequestPostProcessorSupport {

        private SecurityContextRepository repository = new HttpSessionSecurityContextRepository();

        final void save(Authentication authentication, HttpServletRequest request) {
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            save(securityContext, request);
        }

        final void save(SecurityContext securityContext, HttpServletRequest request) {
            HttpServletResponse response = new MockHttpServletResponse();

            HttpRequestResponseHolder requestResponseHolder = new HttpRequestResponseHolder(request, response);
            this.repository.loadContext(requestResponseHolder);

            request = requestResponseHolder.getRequest();
            response = requestResponseHolder.getResponse();

            this.repository.saveContext(securityContext, request, response);
        }
    }

    public final static class SecurityContextRequestPostProcessor
            extends SecurityContextRequestPostProcessorSupport implements RequestPostProcessor {

        private final SecurityContext securityContext;

        private SecurityContextRequestPostProcessor(SecurityContext securityContext) {
            this.securityContext = securityContext;
        }

        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
            save(this.securityContext,request);
            return request;
        }
    }

    public final static class UserRequestPostProcessor
            extends SecurityContextRequestPostProcessorSupport implements RequestPostProcessor {

        private final String username;

        private String rolePrefix = "ROLE_";

        private Object credentials;

        private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        private UserRequestPostProcessor(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
        }

        /**
         * Sets the prefix to append to each role if the role does not already start with
         * the prefix. If no prefix is desired, an empty String or null can be used.
         */
        public UserRequestPostProcessor rolePrefix(String rolePrefix) {
            this.rolePrefix = rolePrefix;
            return this;
        }

        /**
         * Specify the roles of the user to authenticate as. This method is similar to
         * {@link #authorities(org.springframework.security.core.GrantedAuthority...)}, but just not as flexible.
         *
         * @param roles The roles to populate. Note that if the role does not start with
         * {@link #rolePrefix(String)} it will automatically be prepended. This means by
         * default {@code roles("ROLE_USER")} and {@code roles("USER")} are equivalent.
         * @see #authorities(org.springframework.security.core.GrantedAuthority...)
         * @see #rolePrefix(String)
         */
        public UserRequestPostProcessor roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.length);
            for(String role : roles) {
                if(this.rolePrefix == null || role.startsWith(this.rolePrefix)) {
                    authorities.add(new SimpleGrantedAuthority(role));
                } else {
                    authorities.add(new SimpleGrantedAuthority(this.rolePrefix + role));
                }
            }
            return this;
        }

        /**
         * Populates the user's {@link org.springframework.security.core.GrantedAuthority}'s.
         * @param authorities
         * @see #roles(String...)
         */
        public UserRequestPostProcessor authorities(GrantedAuthority... authorities) {
            this.authorities = Arrays.asList(authorities);
            return this;
        }

        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(this.username, this.credentials, this.authorities);
            save(authentication,request);
            return request;
        }
    }

    public final static class UserDetailsRequestPostProcessor
            extends SecurityContextRequestPostProcessorSupport implements RequestPostProcessor {

        private final String username;

        private String userDetailsServiceBeanId;

        private UserDetailsRequestPostProcessor(String username) {
            this.username = username;
        }

        /**
         * Use this method to specify the bean id of the {@link org.springframework.security.core.userdetails.UserDetailsService} to
         * use to look up the {@link org.springframework.security.core.userdetails.UserDetails}.
         *
         * <p>By default a lookup of {@link org.springframework.security.core.userdetails.UserDetailsService} is performed by type. This
         * can be problematic if multiple {@link org.springframework.security.core.userdetails.UserDetailsService} beans are declared.
         */
        public UserDetailsRequestPostProcessor userDetailsServiceBeanId(String userDetailsServiceBeanId) {
            this.userDetailsServiceBeanId = userDetailsServiceBeanId;
            return this;
        }

        public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
            UsernamePasswordAuthenticationToken authentication = authentication(request.getServletContext());
            save(authentication,request);
            return request;
        }

        private UsernamePasswordAuthenticationToken authentication(ServletContext servletContext) {
            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            UserDetailsService  userDetailsService = userDetailsService(context);
            UserDetails userDetails = userDetailsService.loadUserByUsername(this.username);
            return new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }

        private UserDetailsService userDetailsService(ApplicationContext context) {
            if(this.userDetailsServiceBeanId == null) {
                return context.getBean(UserDetailsService.class);
            }
            return context.getBean(this.userDetailsServiceBeanId, UserDetailsService.class);
        }
    }

    private SecurityRequestPostProcessors() {}

}