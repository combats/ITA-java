package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Questions")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String questionId;

    @Column(name = "Question")
    @Expose
    private String QuestionBody;

    @Column(name = "Weight")
    @Expose
    private int weight;
    @ManyToOne
    private User user;

    public Question(String questionBody, int weight) {
        QuestionBody = questionBody;
        this.weight = weight;
    }

    public String getQuestionBody() {
        return QuestionBody;
    }

    public int getWeight() {
        return weight;
    }

    public void setQuestionBody(String questionBody) {
        QuestionBody = questionBody;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (weight != question.weight) return false;
        if (QuestionBody != null ? !QuestionBody.equals(question.QuestionBody) : question.QuestionBody != null)
            return false;
        if (questionId != null ? !questionId.equals(question.questionId) : question.questionId != null) return false;
        if (user != null ? !user.equals(question.user) : question.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId != null ? questionId.hashCode() : 0;
        result = 31 * result + (QuestionBody != null ? QuestionBody.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", QuestionBody='" + QuestionBody + '\'' +
                ", weight=" + weight +
                ", user=" + user +
                '}';
    }
}
