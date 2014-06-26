package com.softserveinc.ita.factoryTests;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 19.06.14
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-interview-factory-test.config.xml")

abstract class BaseInterviewFactoryTests {
}
