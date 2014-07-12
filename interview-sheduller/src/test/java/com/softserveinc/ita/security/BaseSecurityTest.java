package com.softserveinc.ita.security;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-test-servlet.xml",
                                   "classpath:spring-security-test-config.xml"})
public abstract class BaseSecurityTest {

}
