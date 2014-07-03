package com.softserveinc.ita.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group implements Serializable{

    private static final long serialVersionUID = 1L;

    @Version
    private Long version = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "AppointmentId", unique = true)
    private String groupID;

    private List<Applicant> applicantsInGroup = new ArrayList<>();

    public Group() {}

    public Group(String groupID) {
        this.groupID = groupID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (applicantsInGroup != null ? !applicantsInGroup.equals(group.applicantsInGroup) : group.applicantsInGroup != null)
            return false;
        if (groupID != null ? !groupID.equals(group.groupID) : group.groupID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupID != null ? groupID.hashCode() : 0;
        result = 31 * result + (applicantsInGroup != null ? applicantsInGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", applicantsInGroup=" + applicantsInGroup +
                '}';
    }
}
