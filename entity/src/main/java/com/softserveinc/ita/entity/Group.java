package com.softserveinc.ita.entity;

public class Group {

    public enum Status {STATUS1, STATUS2}
    public enum Course {JAVA , SHARP, PYTHON}
    private String groupID;
    private Status groupStatus;
    private Course course;

    public Group() {
    }

    public Group(Status groupStatus, String groupID, Course course){
        this.groupStatus = groupStatus;
        this.groupID = groupID;
        this.course = course;
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
