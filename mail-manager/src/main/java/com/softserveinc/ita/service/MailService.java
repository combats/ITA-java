package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private ApplicantDAO applicantDAO;

    private MimeMessageHelper helper;

    public void sendMail() throws ApplicantDoesNotExistException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String applicantId = "id1";
        Applicant applicant = applicantDAO.getApplicantById(applicantId);
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("javasendertest@gmail.com");
            helper.setTo("braslavskiyandrey@gmail.com");
//            String messageTemplate = readTemplateFromFile(applicant.getStatus().getTemplateRef());
//            helper.setSubject(applicant.getStatus().getSubject());
            ClassPathResource image =
                    new ClassPathResource("D:/SoftServeProject/ITA-java/mail-manager/src/main/resources/example.jpg");
            helper.addInline("logo", image);
            helper.setSubject("some subject");
            helper.setText("<html><body><img src='cid:logo'>" + // HTML-текст
                    "<h4> andrey says...</h4>" +
                    "<i>  hahaha </i>" +
                    "</body></html>", true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
//
//    public void sendMail2() throws MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        helper = new MimeMessageHelper(mimeMessage, true);
//        Map<String, String> model = new HashMap<String, String>();
//        model.put("name", "andrey");
//        model.put("text", "Wow");
//        String emailText = VelocityEngineUtils.mergeTemplateIntoString(
//                velocityEngine, "emailTemplate.vm", model);
//        helper.setFrom("javasendertest@gmail.com");
//        helper.setTo("braslavskiyandrey@gmail.com");
//    }

    private String readTemplateFromFile(String fileName) {
        String template = "";
        try (Scanner in = new Scanner(new FileReader(fileName))) {
            while (in.hasNext()) {
                template += in.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return template;
    }
}