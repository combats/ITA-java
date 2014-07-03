package com.softserveinc.ita.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 25.06.14
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public abstract class BaseMVCTest {

}

