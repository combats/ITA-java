package com.softserveinc.ita.entity;

import com.softserveinc.ita.entity.exceptions.DateException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Appointments")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final long DEFAULT_DURATION_TIME = 30 * 60 * 1000;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String appointmentId;

    @ElementCollection
    @Column(name = "UserIdList")
    private List <String> userIdList = new ArrayList<>();

    @Column(name = "ApplicantId")
    private String applicantId;

    @Column(name = "StartTime")
    private long startTime = System.currentTimeMillis() + TOMORROW;

    @Column(name = "DurationTime")
    private long durationTime = DEFAULT_DURATION_TIME;
    private String groupId;
    private String ownerId;

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

    public Appointment(List<String> userIdList, String applicantId, long startTime, String groupId, String ownerId) {
        this.userIdList = userIdList;
        this.applicantId = applicantId;
        this.startTime = startTime;
        this.groupId = groupId;
        this.ownerId = ownerId;
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

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public void dateValidation(long startTime, long durationTime) throws DateException {

        if (durationTime < 0) throw new DateException("Wrong duration time");

        long currentDate=new Date().getTime();
        if (startTime < currentDate) throw new DateException("Start time has already passed");

        long bigDurationTime = 1000 * 60 * 60 * 12; //interview for the whole day, bad idea
        if (durationTime > bigDurationTime) throw new DateException("Too long duration time");
    }
}
