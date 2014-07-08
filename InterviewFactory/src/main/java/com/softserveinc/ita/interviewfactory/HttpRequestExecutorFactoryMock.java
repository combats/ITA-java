package com.softserveinc.ita.interviewfactory;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Question;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 04.07.14
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class HttpRequestExecutorFactoryMock implements HttpRequestExecutor {

    //-------------VadimNaumenko mock for tests------------------------------------

    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    Appointment appointment1;
    Appointment appointment2;
    Appointment appointment3;

    private User user1 = new User("1", "IT Project Manager");
    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    private Applicant applicant1 = new Applicant("1", "Gena");
    private Applicant applicant2 = new Applicant("2", "Gesha");

    List<String> usersIdList = new ArrayList<String>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<User> usersList = new ArrayList<User>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersList, user1, user2, user3);
    }

    List<Appointment> appointmentList = new ArrayList<>();{
        applicant1.setId("1");
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        applicant2.setId("2");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointment3 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment3.setAppointmentId("3");
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
    }
    //-------------VadimNaumenko mock from tests

    private final int STATUS_CODE_OK = 200;
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public HttpRequestExecutorFactoryMock (String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate  = new RestTemplate();
    }


    @Override
    public <T> T getObjectByID(String id, Class<T> objectClass) throws HttpRequestException {

        // name subdirectory
        String subclass = objectClass.getSimpleName().toLowerCase();

        if (subclass.equals("appointment")){
            for (Appointment appointment : appointmentList){
                if (appointment.getAppointmentId().equals(id)) return (T) appointment;
            }
            throw new HttpRequestException("Appointment not found");
        }
        else if(subclass.equals("user")){
            for (User user : usersList) {
                if (user.getId().equals(id)) {
                    return (T) user;
                }

            }
            throw new HttpRequestException("User not found");
        }
        throw new HttpRequestException("Wrong object");
    }

    @Override
    public List<String> getAllObjectsID(Class objectClass) throws HttpRequestException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RestTemplate getRestTemplate(){ return restTemplate;}

    public String getBaseUrl() {
        return baseUrl;
    }

    private String printException(HttpStatusCodeException ex){
        return   "\nStatus text : " + ex.getStatusText()+
                "\nStatus code : "  +ex.getStatusCode() +
                "\nResponse body : " +  ex.getResponseBodyAsString();
    }


}
