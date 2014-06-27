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
import org.springframework.ui.velocity.VelocityEngineUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private ApplicantDAO applicantDAO;

    private MimeMessageHelper helper;

    public void sendMailtoApplicant() throws ApplicantDoesNotExistException {
        String applicantId = "id1";
        Applicant applicant = applicantDAO.getApplicantById(applicantId);
    }

    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        helper = new MimeMessageHelper(mimeMessage, true);
        Map<String, String> model = new HashMap<String, String>();
        model.put("name", "andrey");
        model.put("surname", "Braslavskiy");
        model.put("time", "11:00");
        model.put("date", "14.08.2014");
        model.put("assistantName", "Lena");
        model.put("assistantSurname", "Golovach");
        model.put("assistantPhone", "0663459412");
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "interviewInvitation.vm", model );
        helper.setFrom("javasendertest@gmail.com");
        helper.setTo("braslavskiyandrey@gmail.com");
        helper.setText(emailText,true);
        ClassPathResource image = new ClassPathResource("softServe.jpg");
        helper.addInline("ItAcademyLogo", image);
        mailSender.send(mimeMessage);
    }

    private void sendSchedulingLetter(){

    }

    private void sendNotPassedInterviewLetter(){

    }

    private void sendPassedInterviewLetter(){

    }
}