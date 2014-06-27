package com.softserveinc.ita.controller;

import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.service.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.mail.MessagingException;

public class MailerTest {


    public static void main(String[] args) throws MessagingException {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-config.xml");
        MailService mailer = (MailService) context.getBean("mailService");
        MailerTest mailerTest = new MailerTest();
            mailer.sendMail();
    }
}