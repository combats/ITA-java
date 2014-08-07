package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service("mailService")
public class MailService {

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String FROM = "javasendertest@gmail.com";
    public static final String LOGO = "ItAcademyLogo";
    public static final String LOGO_IMAGE_REF = "images/softServe.jpg";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String DATE_FORMAT = "HH:mm:ss:SSS";

    @Autowired
    private JavaMailSender mailSender;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private VelocityEngine velocityEngine;

    private MimeMessageHelper helper;
    private MimeMessage mimeMessage;


    public void sendMail() throws MessagingException {
        mimeMessage = mailSender.createMimeMessage();
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
                velocityEngine, "mailTemplaits/interviewInvitation.vm", model);
        helper.setFrom("ita@itsve.tk");
        helper.setTo("braslavskiyandrey@gmail.com");
        helper.setText(emailText,true);
        ClassPathResource image = new ClassPathResource("images/softServe.jpg");
        helper.addInline("ItAcademyLogo", image);
        mailSender.send(mimeMessage);
    }

    private void sendSchedulingLetter(Applicant applicant, Appointment appointment) {
        //name,surname, interview startTime, interview date, hr name,surname and phone
        Map<String, String> model = new HashMap<>();
        model.put(NAME, applicant.getName());
        model.put(SURNAME, applicant.getSurname());
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(appointment.getStartTime());
        model.put(TIME, formatter.format(calendar.getTime()));
        // TODO implement HR logic
    }

    private void sendNotPassedInterviewLetter(Applicant applicant) {
        Map<String, String> model = new HashMap<>();
        model.put(NAME, applicant.getName());
        model.put(SURNAME, applicant.getSurname());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, applicant.getStatus().getTemplateRef(), model);
        try {
            helper.setFrom(FROM);
            helper.setTo(applicant.getEmail());
            helper.setText(emailText, true);
            ClassPathResource image = new ClassPathResource(LOGO_IMAGE_REF);
            helper.addInline(LOGO, image);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendPassedInterviewLetter(Applicant applicant, Appointment appointment) {
        //applicant name and surname,  Group course, group startTime , group StartDate, hr name and surname , hr phone
        Map<String, String> model = new HashMap<>();
        model.put(NAME, applicant.getName());
        model.put(SURNAME, applicant.getSurname());
        //TODO implement group and HR logic
    }

    private String convertTimeToDate(long milliseconds) {

        return null;
    }
}