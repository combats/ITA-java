package com.softserveinc.ita.interviewfactory.dao.mock;

import com.softserveinc.ita.dao.InterviewDAO;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 15.07.14
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class InterviewDAOMock implements InterviewDAO {

    @Autowired
    InterviewFactory interviewFactory;

    List<Interview> interviewsList = new ArrayList<>();

    @Override
    public Interview getInterviewByAppointmentId(String appointmentId) {

        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);


        for (Interview interview : interviewsList){
            if (interview.getInterviewId().equals(appointmentId)) return interview;
        }
        return null;
    }

    @Override
    public String putInterview(Interview interview){

        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);

        interviewsList.add(interview);
        return interview.getInterviewId();
    }

    @Override
    public void removeInterviewByAppointmentId(String appointmentId) {

        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);

        for (int i = 0; i < interviewsList.size(); i++){
            if (interviewsList.get(i).getInterviewId().equals(appointmentId)) interviewsList.remove(i);
        }
    }

    @Override
    public void updateInterview(Interview interview) {

        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);

        for (int i = 0; i < interviewsList.size(); i++){
            if (interviewsList.get(i).getInterviewId().equals(interview.getInterviewId())) {
                interviewsList.add(i, interview);
                break;
            }

        }
    }

    @Override
    public List<String> getAllInterviewsId(){

        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);

        List<String> interviewsIdList = new ArrayList<>();
        for (Interview interview : interviewsList){
            interviewsIdList.add(interview.getInterviewId());
        }
        return interviewsIdList;
    }

    @Override
    public List<Interview> getAllInterviews() {
        List<Interview> interviewsList = new ArrayList<>();
        Interview interview1 = null;
        try {
            interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Interview interview2 = null;
        try {
            interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");
        } catch (HttpRequestException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        interviewsList.add(interview1);
            interviewsList.add(interview2);


        return interviewsList;
    }
}
