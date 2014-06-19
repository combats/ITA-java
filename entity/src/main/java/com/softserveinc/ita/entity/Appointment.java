package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Appointments")
public class Appointment {

    private static final long DEFAULT_DURATION_TIME = 30 * 60 * 1000;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "AppointmentId", unique = true)
    private String appointmentId;

    @ElementCollection
    @CollectionTable(name = "Users", joinColumns = @JoinColumn(name = "UserId"))
	@Column(name = "UserIdList")
	private List <String> userIdList = new ArrayList<>();

    @Column(name = "ApplicantId")
	private String applicantId;

    @Column(name = "StartTime")
	private long startTime = System.currentTimeMillis();

    @Column(name = "DurationTime")
	private long durationTime = DEFAULT_DURATION_TIME;

	public Appointment() {}

	public Appointment(List<String> userIdList, String applicantId, long startTime, long durationTime) {
		this.userIdList = userIdList;
		this.applicantId = applicantId;
		this.startTime = startTime;
		this.durationTime = durationTime;
	}

	public Appointment(List<String> userIdList, String applicantId, long startTime) {
		this.userIdList = userIdList;
		this.applicantId = applicantId;
		this.startTime = startTime;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(long durationTime) {
		this.durationTime = durationTime;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"userIdList=" + userIdList +
				", applicantId=" + applicantId +
				", startTime=" + startTime +
				", durationTime=" + durationTime +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Appointment that = (Appointment) o;

		if (durationTime != that.durationTime) return false;
		if (startTime != that.startTime) return false;
		if (!applicantId.equals(that.applicantId)) return false;
		if (!userIdList.equals(that.userIdList)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userIdList.hashCode();
		result = 31 * result + applicantId.hashCode();
		result = 31 * result + (int) (startTime ^ (startTime >>> 32));
		result = 31 * result + (int) (durationTime ^ (durationTime >>> 32));
		return result;
	}
}
