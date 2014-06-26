package com.softserveinc.ita.controller;

import com.softserveinc.ita.service.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MailerTest {


    public static void main(String[] args)
    {

        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-config.xml");

        //Get the mailer instance
        MailService mailer = (MailService) context.getBean("mailService");

        MailerTest mailerTest = new MailerTest();

        //Send a composed mail
        mailer.sendMail("braslavskiyandrey@gmail.com", "Test Subject", "Mime message example");
    }
}