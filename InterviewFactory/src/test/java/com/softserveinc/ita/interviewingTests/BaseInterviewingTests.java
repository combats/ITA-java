package com.softserveinc.ita.interviewingTests;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 08.07.14
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})

abstract class BaseInterviewingTests {
}
