package com.softserveinc.ita.controller;

import com.softserveinc.ita.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

public class MailerTest {

    public static void main(String[] args) throws MessagingException {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:manager-spring-config.xml");
        MailService mailer = (MailService) context.getBean("mailService");
        mailer.sendMail();
    }
}