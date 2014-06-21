package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */

public class Interview {

    @Expose
    private String appointmentId;
    @Expose
    private List<QuestionsBlock> answerBlocks = new ArrayList<>();
    @Expose
    private String finalComment = "";

    private InterviewType type;

    public Interview() {
    }

    public Interview(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Interview(String appointmentId, InterviewType type) {
        this.appointmentId = appointmentId;
        this.type = type;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }


    //    public void editAnswersByUser(User user,List<String> Answers, List<Integer> Mark, List<String> Comment,
//                                  String FinalComment, int BonusPoints){
//
//        QuestionsBlock answerBlock = getApplicantAnswersByUser(user);
//        answerBlock.setAnswers(Answers);
//        answerBlock.setMark(Mark);
//        answerBlock.setComment(Comment);
//        answerBlock.setFinalComment(FinalComment);
//        answerBlock.setBonusPoints(BonusPoints);
//        setApplicantAnswersByUser(user, answerBlock);
//
//    }
//
//    public QuestionsBlock getApplicantAnswersByUser(User user){
//        for (QuestionsBlock answer : answerBlocks) if (answer.getUser().equals(user)) return answer;
//        return null;
//    }
//
//    public void setApplicantAnswersByUser(User user, QuestionsBlock CorrectedAnswer){
//        for (int i = 0; i < answerBlocks.size(); i++)
//            if (answerBlocks.get(i).getUser().equals(user))
//                answerBlocks.set(i, CorrectedAnswer);
//
//    }




    public List<QuestionsBlock> getAnswerBlocks() {
        return answerBlocks;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAnswerBlocks(List<QuestionsBlock> answerBlocks) {
        this.answerBlocks = answerBlocks;
    }

    public String getFinalComment() {
        return finalComment;
    }

    public void setFinalComment(String finalComment) {
        this.finalComment = finalComment;
    }
}
