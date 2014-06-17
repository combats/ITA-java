package com.softserveinc.ita.entity;

import com.softserveinc.ita.entity.exceptions.DateException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment {
	private static final long DEFAULT_DURATION_TIME = 30 * 60 * 1000;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;
	private List <String> userIdList = new ArrayList<>();
	private List <String> applicantIdList = new ArrayList<>();
    private long startTime = System.currentTimeMillis() + TOMORROW;
	private long durationTime = DEFAULT_DURATION_TIME;

	public Appointment() {}

	public Appointment(List<String> userIdList, List<String> applicantIdList, long startTime, long durationTime) {
		this.userIdList = userIdList;
		this.applicantIdList = applicantIdList;
		this.startTime = startTime;
		this.durationTime = durationTime;
	}

	public Appointment(List<String> userIdList, List<String> applicantIdList, long startTime) {
		this.userIdList = userIdList;
		this.applicantIdList = applicantIdList;
		this.startTime = startTime;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public List<String> getApplicantIdList() {
		return applicantIdList;
	}

	public void setApplicantIdList(List<String> applicantIdList) {
		this.applicantIdList = applicantIdList;
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
				", applicantIdList=" + applicantIdList +
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
		if (!applicantIdList.equals(that.applicantIdList)) return false;
		if (!userIdList.equals(that.userIdList)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userIdList.hashCode();
		result = 31 * result + applicantIdList.hashCode();
		result = 31 * result + (int) (startTime ^ (startTime >>> 32));
		result = 31 * result + (int) (durationTime ^ (durationTime >>> 32));
		return result;
	}

    public void dateValidation(long startTime, long durationTime) throws DateException {

        if (durationTime < 0) throw new DateException("Wrong duration time");

        long currentDate=new Date().getTime();
        if (startTime < currentDate) throw new DateException("Start time has already passed");

        long bigDurationTime = 1000 * 60 * 60 * 12; //interview for the whole day, bad idea
        if (durationTime > bigDurationTime) throw new DateException("Too long duration time");
    }
}
