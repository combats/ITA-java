package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "QuestionInformation")
public class QuestionInformation implements Serializable {

    @Expose
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "questionInformation_id", unique = true)
    private String Id;

    @Expose
    @Column(name = "Interview_id")
    private String interviewId;

    @Expose
    @Column(name = "Question")
    private String question = "";

    @Expose
    @Column(name = "Answer")
    private String answer = "";

    @Expose
    @Column(name = "Mark")
    private int mark;

    @Expose
    @Column(name = "Comment")
    private String comment = "";

    @Column(name = "Weight")
    private int weight; //weight for each question

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questionsBlock_id")
    private QuestionsBlock questionsBlock;

    public QuestionInformation() {
    }

    public QuestionInformation(String question, int weight) {
        this.question = question;
        this.weight = weight;
    }

    public QuestionInformation(String question, String answer, int mark, String comment, int weight) {
        this.question = question;
        this.answer = answer;
        this.mark = mark;
        this.comment = comment;
        this.weight = weight;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public int getWeight() {
        return weight;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }


    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public QuestionsBlock getQuestionsBlock() {
        return questionsBlock;
    }

    public void setQuestionsBlock(QuestionsBlock questionsBlock) {
        this.questionsBlock = questionsBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionInformation)) return false;

        QuestionInformation that = (QuestionInformation) o;

        if (mark != that.mark) return false;
        if (weight != that.weight) return false;
        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (interviewId != null ? !interviewId.equals(that.interviewId) : that.interviewId != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (questionsBlock != null ? !questionsBlock.equals(that.questionsBlock) : that.questionsBlock != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (questionsBlock != null ? questionsBlock.hashCode() : 0);
        result = 31 * result + (interviewId != null ? interviewId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionInformation{" +
                "Id='" + Id + '\'' +
                ", questionsBlock=" + questionsBlock +
                ", interviewId='" + interviewId + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", weight=" + weight +
                '}';
    }
}
