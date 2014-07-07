package com.softserveinc.ita.interviewfactory.service.interviewingServices;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.mainServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 02.07.14
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class InterviewingServicesMock implements InterviewingService {

    //-------------VadimNaumenko mock for tests------------------------------------
    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;


    @Autowired
    InterviewService interviewService;

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
        List<Question> questionsList1 = new ArrayList<Question>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<Question>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<Question>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<String> appointmentIdList = new ArrayList<String>();{
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointment3 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment3.setAppointmentId("3");
        appointmentIdList.add(appointment1.getAppointmentId());
        appointmentIdList.add(appointment2.getAppointmentId());
        appointmentIdList.add(appointment3.getAppointmentId());
    }

    //Standard questions mock

    QuestionsBlock standardQuestionsBlock = new QuestionsBlock();{
    QuestionInformation questionInformation1 = new QuestionInformation();
    questionInformation1.setQuestion("What is your name?");
    questionInformation1.setWeight(2);
    QuestionInformation questionInformation2 = new QuestionInformation();
    questionInformation2.setQuestion("How are you?");
    questionInformation2.setWeight(3);

    List<QuestionInformation> QuestionInformationList = new ArrayList<QuestionInformation>();{
    QuestionInformationList.add(questionInformation1);
    QuestionInformationList.add(questionInformation2);
    }

    standardQuestionsBlock.setQuestions(QuestionInformationList);
    }


    //-------------VadimNaumenko mock from tests -------------------------------------------------------


    @Override
         public QuestionsBlock getQuestionsBlockByUserId(String userID, String interviewID) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException {

        List<Interview> interviewsList = new ArrayList<Interview>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interview1.setInterviewId("1");
            interview2.setInterviewId("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        List<QuestionsBlock> questionsBlocks = interviewService.getInterviewByInterviewID(interviewID).getQuestionsBlocks();

        for (QuestionsBlock questionsBlock : questionsBlocks){
            if (questionsBlock.getUser().getId().equals(userID))
                return questionsBlock;
        }

        throw new QuestionsBlockNotFound("Wrong userID or interviewID");
    }

    @Override
    public QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId, String interviewID) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException {

        List<Interview> interviewsList = new ArrayList<Interview>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interview1.setInterviewId("1");
            interview2.setInterviewId("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        List<QuestionsBlock> questionsBlocks = interviewService.getInterviewByInterviewID(interviewID).getQuestionsBlocks();

        for (QuestionsBlock questionsBlock : questionsBlocks){
            if (questionsBlock.getQuestionsBlockID().equals(questionsBlockId))
                return questionsBlock;
        }

        throw new QuestionsBlockNotFound("Wrong userID or interviewID");
    }

    @Override
    public QuestionsBlock updateQuestionsBlock(String interviewID, QuestionsBlock newQuestionsBlock) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException {

        List<Interview> interviewsList = new ArrayList<Interview>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interview1.setInterviewId("1");
            interview2.setInterviewId("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        Interview interview = interviewService.getInterviewByInterviewID(interviewID);
        List<QuestionsBlock> questionsBlocks = interview.getQuestionsBlocks();

        for (int i = 0; i < questionsBlocks.size(); i++){
            if (questionsBlocks.get(i).getQuestionsBlockID().equals(newQuestionsBlock.getQuestionsBlockID()))
                questionsBlocks.add(i, newQuestionsBlock);
        }

        interview.setQuestionsBlocks(questionsBlocks);
        interviewService.setInterview(interview);
        return newQuestionsBlock;
    }

    @Override
    public QuestionInformation answerForUserQuestion(String interviewID, String questionsBlockID, QuestionInformation questionInformation) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException {

        List<Interview> interviewsList = new ArrayList<Interview>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interview1.setInterviewId("1");
            interview2.setInterviewId("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        QuestionsBlock questionsBlock = getQuestionsBlockByQuestionsBlockId(interviewID, questionsBlockID);
        List<QuestionInformation> questionsList = questionsBlock.getQuestions();

        for (int i = 0; i < questionsList.size(); i++){
            if (questionsList.get(i).getQuestionInformationID().equals(questionInformation.getQuestionInformationID())) {
                questionsList.add(i, questionInformation);
            }
        }

        questionsBlock.setQuestions(questionsList);
        updateQuestionsBlock(interviewID, questionsBlock);

        return questionInformation;
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QuestionsBlock answerForStandardQuestion(String interviewID, String questionsBlockID, QuestionInformation questionInformation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlock() {
        return standardQuestionsBlock;
    }

    @Override
    public QuestionsBlock setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock) {
        this.standardQuestionsBlock = standardQuestionsBlock;
        return getStandardQuestionsBlock();
    }

    @Override
    public int getTotalPointsByInterviewID(String interviewID) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
