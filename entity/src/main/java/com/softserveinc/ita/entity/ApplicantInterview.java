package com.softserveinc.ita.entity;



import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ApplicantInterview")
public class ApplicantInterview {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;

	@Column(name = "applicantId")
	private String applicantId;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Column(name = "usersId")
	private List<String> usersId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<InterviewQuestion> questions;

	@Column(name = "finalComment")
	private String finalComment;

	public ApplicantInterview() {
	}

	public ApplicantInterview(String applicantId, List<String> usersId, List<InterviewQuestion> questions, String finalComment) {
		this.applicantId = applicantId;
		this.usersId = usersId;
		this.questions = questions;
		this.finalComment = finalComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public List<String> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<String> usersId) {
		this.usersId = usersId;
	}

	public List<InterviewQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<InterviewQuestion> questions) {
		this.questions = questions;
	}

	public String getFinalComment() {
		return finalComment;
	}

	public void setFinalComment(String finalComment) {
		this.finalComment = finalComment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ApplicantInterview applicantInterview = (ApplicantInterview) o;

		if (applicantId != null ? !applicantId.equals(applicantInterview.applicantId) : applicantInterview.applicantId != null)
			return false;
		if (finalComment != null ? !finalComment.equals(applicantInterview.finalComment) : applicantInterview.finalComment != null)
			return false;
		if (questions != null ? !questions.equals(applicantInterview.questions) : applicantInterview.questions != null)
			return false;
		if (usersId != null ? !usersId.equals(applicantInterview.usersId) : applicantInterview.usersId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = applicantId != null ? applicantId.hashCode() : 0;
		result = 31 * result + (usersId != null ? usersId.hashCode() : 0);
		result = 31 * result + (questions != null ? questions.hashCode() : 0);
		result = 31 * result + (finalComment != null ? finalComment.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ApplicantInterview{" +
				"id='" + id + '\'' +
				", applicantId='" + applicantId + '\'' +
				", usersId=" + usersId +
				", questions=" + questions +
				", finalComment='" + finalComment + '\'' +
				'}';
	}
}