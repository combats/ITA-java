package com.softserveinc.ita.entity;

import java.io.Serializable;
import java.util.*;

public class Group implements Serializable {
    private String ID = "";
    private Map<String, ApplicantBenchmark> applicants = new HashMap<>();
    private String name = "KV-XX";
    private int capacity = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Group() {
    }

    @Override
    public String toString() {
        return "Group{" +
                "ID='" + ID + '\'' +
                ", applicants=" + applicants +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (capacity != group.capacity) return false;
        if (!ID.equals(group.ID)) return false;
        if (!applicants.equals(group.applicants)) return false;
        if (!name.equals(group.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + applicants.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + capacity;
        return result;
    }

    public Group(String ID, String name, int capacity) {
        this.ID = ID;
        this.name = name;
        this.capacity = capacity;
    }

    public Set<String> getApplicantsIDList() {
        return applicants.keySet();
    }

    public void addNewApplicant(String applicantID) {
        applicants.put(applicantID, new ApplicantBenchmark(Applicant.Status.NOT_SCHEDULED, -1));
    }

    public void addOrUpdateApplicantIDListByStatus(List<String> applicantIDs, ApplicantBenchmark benchmark) {
        for (String id : applicantIDs) {
            applicants.put(id, benchmark);
        }
    }

    public Set<String> getApplicantsIDListByStatus(Applicant.Status status) {
        Set<String> keys = new HashSet<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            if (status.equals(entry.getValue().getStatus())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public Map<String, ApplicantBenchmark> getApplicants() {
        return applicants;
    }

    public void addOrUpdateApplicantIDListByStatus(Map<String, ApplicantBenchmark> applicants) {
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            this.applicants.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, ApplicantBenchmark> getApplicantsByStatus(Applicant.Status status) {
        Map<String, ApplicantBenchmark> result = new HashMap<>();
        for (Map.Entry<String, ApplicantBenchmark> entry : applicants.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
