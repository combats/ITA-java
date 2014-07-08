package com.softserveinc.ita.controller;

import com.softserveinc.ita.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import java.lang.reflect.Method;

@Controller
@RequestMapping("/")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "send", method = RequestMethod.GET)
    public void initMailSending() throws MessagingException {
        mailService.sendMail();
    }

}
