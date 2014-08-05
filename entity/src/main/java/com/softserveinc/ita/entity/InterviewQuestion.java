package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "InterviewQuestions")
public class InterviewQuestion {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "question_id", unique = true)
	private String id;

	@Column(name = "question")
	@NotEmpty
	private String question;

    @Column(name = "userId")
    private String userId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "mark")
	@Range(min = 0, max = 10)
	private int mark;

	@Column(name = "weight")
	@Range(min = 1, max = 4)
	private int weight;

	public InterviewQuestion() {
	}

	public InterviewQuestion(String question, String comment, int mark, int weight) {
		this.question = question;
		this.comment = comment;
		this.mark = mark;
		this.weight = weight;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterviewQuestion)) return false;

        InterviewQuestion that = (InterviewQuestion) o;

        if (mark != that.mark) return false;
        if (weight != that.weight) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + mark;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "InterviewQuestion{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", mark=" + mark +
                ", weight=" + weight +
                '}';
    }
}