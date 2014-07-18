package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Groups")
public class Group implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final String IN_PROCESS_STATUS = "In process";
    public static final String PLANNED_STATUS = "Planned";
    public static final String OFFERING_STATUS = "Offering";
    public static final String BOARDING_STATUS = "Boarding";
    public static final String FINISHED_STATUS = "Finished";


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String groupID;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @JoinTable(name = "ApplicantsWithStatus", joinColumns = @JoinColumn(name = "Id"))
    @MapKeyColumn(name = "ApplicantId")
    @Column(name = "ApplicantsStatus")
    private Map<String, Applicant.Status> applicants = new HashMap<>();
    @Column(name = "GroupName")
    private String groupName;
    @Column(name = "GroupStatus")
    private Status groupStatus;
    private long startTime;
    private long endTime;
    private int capacity;
    private String address;
    private Course course;

    public enum Status implements Serializable {
        IN_PROCESS(IN_PROCESS_STATUS), PLANNED(PLANNED_STATUS), OFFERING(OFFERING_STATUS), BOARDING(BOARDING_STATUS),
        FINISHED(FINISHED_STATUS);
        private String name;
        Status(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    public Group() {}

    public Group(String groupID) {
        this.groupID = groupID;
    }

    public Group(String groupID, Course course, String groupName) {
        this.groupID = groupID;
        this.course = course;
        this.groupName = groupName;
    }
    public Group(Status groupStatus, String groupID, Course course, String groupName) {
        this.groupStatus = groupStatus;
        this.groupID = groupID;
        this.course = course;
        this.groupName = groupName;
    }
    public Group(String groupId, Course course, String address, long startTime) {
        this.groupID = groupId;
        this.course = course;
        this.address = address;
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Map<String, Applicant.Status> getApplicants() {
        return applicants;
    }

    public void setApplicants(Map<String, Applicant.Status> applicants) {
        this.applicants = applicants;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Status getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Status groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (capacity != group.capacity) return false;
        if (endTime != group.endTime) return false;
        if (startTime != group.startTime) return false;
        if (!address.equals(group.address)) return false;
        if (!applicants.equals(group.applicants)) return false;
        if (!course.equals(group.course)) return false;
        if (!groupID.equals(group.groupID)) return false;
        if (!groupName.equals(group.groupName)) return false;
        if (groupStatus != group.groupStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupID.hashCode();
        result = 31 * result + applicants.hashCode();
        result = 31 * result + groupName.hashCode();
        result = 31 * result + groupStatus.hashCode();
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + capacity;
        result = 31 * result + address.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", applicants=" + applicants +
                ", groupName='" + groupName + '\'' +
                ", groupStatus=" + groupStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", capacity=" + capacity +
                ", address='" + address + '\'' +
                ", course=" + course +
                '}';
    }
}