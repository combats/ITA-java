package com.softserveinc.ita.interviewfactory.dao.interviewDAO;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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

    @Qualifier("interviewFactory")
    @Autowired
    InterviewFactory interviewFactory;

    List<Interview> interviewsList = new ArrayList<>();

    @Override
    public Interview getInterviewByAppointmentId(String appointmentId) throws HttpRequestException {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);


        for (Interview interview : interviewsList){
            if (interview.getInterviewId().equals(appointmentId)) return interview;
        }
        return null;
    }

    @Override
    public String putInterview(Interview interview) throws HttpRequestException {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);

        interviewsList.add(interview);
        return interview.getInterviewId();
    }

    @Override
    public void removeInterviewByAppointmentId(String appointmentId) throws HttpRequestException {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);

        for (int i = 0; i < interviewsList.size(); i++){
            if (interviewsList.get(i).getInterviewId().equals(appointmentId)) interviewsList.remove(i);
        }
    }

    @Override
    public void updateInterview(Interview interview) throws HttpRequestException {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);

        for (int i = 0; i < interviewsList.size(); i++){
            if (interviewsList.get(i).getInterviewId().equals(interview.getInterviewId())) interviewsList.add(i, interview);
        }
    }

    @Override
    public List<String> getAllInterviewsId() throws HttpRequestException {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);

        List<String> interviewsIdList = new ArrayList<>();
        for (Interview interview : interviewsList){
            interviewsIdList.add(interview.getInterviewId());
        }
        return interviewsIdList;
    }

    @Override
    public List<Interview> getAllInterviews() throws HttpRequestException {
        List<Interview> interviewsList = new ArrayList<>();
            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
            interviewsList.add(interview1);
            interviewsList.add(interview2);


        return interviewsList;
    }
}
