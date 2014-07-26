package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.CourseDAO;
import com.softserveinc.ita.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoMockImpl implements CourseDAO {

    @Override
    public Course addCourse(Course course) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("DevOps", "pen-devops.png"));
        courses.add(new Course("JavaScript", "pen-jsui.png"));
        courses.add(new Course("Java", "pen-java.png"));
        courses.add(new Course("Sharp", "pen-net.png"));
        return courses;
    }

    @Override
    public void removeCourse(Course course) {

    }
}
