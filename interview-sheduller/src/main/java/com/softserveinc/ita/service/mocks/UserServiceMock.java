package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Question;
import com.softserveinc.ita.entity.User;

import com.softserveinc.ita.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceMock implements UserService {

    private User user1 = new User("1", "IT Project Manager");
    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    List<User> usersList = new ArrayList<User>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersList, user1, user2, user3);
    }


    public boolean userExists(String userId){
   
        return userId.equals("testUserId");
    }

}