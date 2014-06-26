package com.softserveinc.ita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailService {
    @Autowired
    private JavaMailSender mailSender;


    private MimeMessageHelper helper;

    public void sendMail(String to, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("javasendertest@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource couponImage =
                    new FileSystemResource("/ITA-java/mail-manager/src/main/resources/example.jpg");
            helper.addAttachment("Coupon.png", couponImage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}