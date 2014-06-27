package com.softserveinc.ita.controller;

import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.service.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MailerTest {


    public static void main(String[] args)
    {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        MailService mailer = (MailService) context.getBean("mailService");
        MailerTest mailerTest = new MailerTest();
        try {
            mailer.sendMail();
        } catch (ApplicantDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}