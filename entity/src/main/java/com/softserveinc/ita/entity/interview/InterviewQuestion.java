package com.softserveinc.ita.entity.interview;

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

	@Column(name = "comment")
	@NotEmpty
	private String comment;

	@Column(name = "mark")
	@Range(min = 1, max = 10)
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
		if (o == null || getClass() != o.getClass()) return false;

		InterviewQuestion question1 = (InterviewQuestion) o;

		if (mark != question1.mark) return false;
		if (weight != question1.weight) return false;
		if (comment != null ? !comment.equals(question1.comment) : question1.comment != null) return false;
		if (id != null ? !id.equals(question1.id) : question1.id != null) return false;
		if (question != null ? !question.equals(question1.question) : question1.question != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (question != null ? question.hashCode() : 0);
		result = 31 * result + (comment != null ? comment.hashCode() : 0);
		result = 31 * result + mark;
		result = 31 * result + weight;
		return result;
	}

	@Override
	public String toString() {
		return "Question{" +
				"id='" + id + '\'' +
				", question='" + question + '\'' +
				", comment='" + comment + '\'' +
				", mark=" + mark +
				", weight=" + weight +
				'}';
	}
}