package com.softserveinc.ita.exception.impl;

import com.softserveinc.ita.exception.GroupException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Course with such name or reference already exists")
public class CourseAlreadyExists extends GroupException {

    public CourseAlreadyExists() {
    }

    public CourseAlreadyExists(String message) {
        super(message);
    }
}