package com.softserveinc.ita.entity;

import java.io.Serializable;

public class Course implements Serializable {

    private String name;
    private String courseId;
    private String imageRef;

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String name, String imageRef) {
        this.name = name;
        this.imageRef = imageRef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", courseId='" + courseId + '\'' +
                ", imageRef='" + imageRef + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!courseId.equals(course.courseId)) return false;
        if (!imageRef.equals(course.imageRef)) return false;
        if (!name.equals(course.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + courseId.hashCode();
        result = 31 * result + imageRef.hashCode();
        return result;
    }
}
