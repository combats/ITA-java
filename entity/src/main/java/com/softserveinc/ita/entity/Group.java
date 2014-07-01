package com.softserveinc.ita.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable{
    private String groupID;
    private List<Applicant> applicantsInGroup = new ArrayList<>();
    private String groupName;
    private Status groupStatus;
    private Course course;
    
    public Group() {}

    public Group(String groupID) {
        this.groupID = groupID;
    }

    public Group(Status groupStatus, String groupID, Course course, String groupName) {
        this.groupStatus = groupStatus;
        this.groupID = groupID;
        this.course = course;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public List<Applicant> getApplicantsInGroup() {
        return applicantsInGroup;
    }

    public void setApplicantsInGroup(List<Applicant> applicantsInGroup) {
        this.applicantsInGroup = applicantsInGroup;
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

}
