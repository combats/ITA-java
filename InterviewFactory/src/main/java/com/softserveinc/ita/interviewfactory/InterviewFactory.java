package com.softserveinc.ita.interviewfactory;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;

import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;

import com.softserveinc.ita.exceptions.InvalidUserIDException;
import exceptions.WrongCriteriaException;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 20.06.14
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class InterviewFactory {

    public static Interview getInterview(String appointment, InterviewType type)
            throws WrongCriteriaException, InvalidUserIDException, ApppoinmentNotFoundException {

        if (type.equals(InterviewType.InterviewWithoutQuestions)) {
            InterviewWithoutQuestions interviewWithoutQuestions = new InterviewWithoutQuestions(appointment);
            interviewWithoutQuestions.createInterview();
            return interviewWithoutQuestions;
        }
        else
        if (type.equals(InterviewType.InterviewWithStandardQuestions)){
            InterviewWithStandardQuestions interviewWithQuestions = new InterviewWithStandardQuestions(appointment);
            interviewWithQuestions.createInterview();
            return interviewWithQuestions;
        }
        else
        if(type.equals(InterviewType.InterviewWithUserAndStandardQuestions)){
            InterviewWithUserAndStandardQuestions interviewWithUserAndStandardQuestions =
                    new InterviewWithUserAndStandardQuestions(appointment);
            interviewWithUserAndStandardQuestions.createInterview();
            return interviewWithUserAndStandardQuestions;
        }
        else throw new WrongCriteriaException("Wrong criteria");
    }
}
