package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    ApplicantDAO applicantDAO;

    private MimeMessageHelper helper;

    public void sendMail(ArrayList<String> applicantsId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("javasendertest@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource couponImage =
                    new FileSystemResource("D:\\SoftServeProject\\ITA-java\\mail-manager\\src\\main\\resources\\example.jpg");
            helper.addAttachment("Coupon.png", couponImage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}