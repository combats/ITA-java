package com.softserveinc.ita.entity;

public class InterviewResult {
    private String finalComment = "";
    private int totalPoints = 0;

    public InterviewResult() {
    }

    @Override
    public String toString() {
        return "InterviewResult{" +
                "finalComment='" + finalComment + '\'' +
                ", totalPoints=" + totalPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterviewResult that = (InterviewResult) o;

        if (totalPoints != that.totalPoints) return false;
        if (!finalComment.equals(that.finalComment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = finalComment.hashCode();
        result = 31 * result + totalPoints;
        return result;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getFinalComment() {
        return finalComment;
    }

    public void setFinalComment(String finalComment) {
        this.finalComment = finalComment;
    }

    public InterviewResult(String finalComment, int totalPoints) {
        this.finalComment = finalComment;
        this.totalPoints = totalPoints;
    }
}
