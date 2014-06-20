package com.softserveinc.ita.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable{
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
