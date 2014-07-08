package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group implements Serializable{

    public static final String IN_PROCESS_STATUS = "In process";
    public static final String PLANNED_STATUS = "Planned";
    public static final String OFFERING_STATUS = "Offering";
    public static final String BOARDING_STATUS = "Boarding";
    private static final long serialVersionUID = 1L;

    public enum Status implements Serializable {
        IN_PROCESS(IN_PROCESS_STATUS), PLANNED(PLANNED_STATUS), OFFERING(OFFERING_STATUS), BOARDING(BOARDING_STATUS);
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

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "Id", unique = true)
    private String groupID;

    @ElementCollection
    @CollectionTable(name = "ApplicantsInGroup", joinColumns = @JoinColumn(name = "Id"))
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
    public Status getGroupStatus() {
        return groupStatus;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", applicantsInGroup=" + applicantsInGroup +
                ", groupName='" + groupName + '\'' +
                ", groupStatus=" + groupStatus +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!applicantsInGroup.equals(group.applicantsInGroup)) return false;
        if (!course.equals(group.course)) return false;
        if (!groupID.equals(group.groupID)) return false;
        if (!groupName.equals(group.groupName)) return false;
        if (groupStatus != group.groupStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupID.hashCode();
        result = 31 * result + applicantsInGroup.hashCode();
        result = 31 * result + groupName.hashCode();
        result = 31 * result + groupStatus.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }
}
