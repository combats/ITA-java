package com.softserveinc.ita.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class Group implements Serializable {

    public enum Status implements Serializable {

        IN_PROGRESS("In progress"), RECRUITMENT("Recruitment");
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

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Course implements Serializable {

        JAVA("Java"), SHARP("Sharp"), DEVOPS("DevOps"), JAVASCRIPT("JavaScript");
        private String name;

        Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private String groupID;
    private String groupName;
    private Status groupStatus;
    private Course course;

    public Group() {
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
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", groupStatus=" + groupStatus +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        if (course != group.course) return false;
        if (!groupID.equals(group.groupID)) return false;
        if (groupStatus != group.groupStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupID.hashCode();
        result = 31 * result + groupStatus.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }
}
