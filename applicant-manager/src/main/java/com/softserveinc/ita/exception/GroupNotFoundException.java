package com.softserveinc.ita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Group does not exists")
public class GroupNotFoundException extends Exception {
    public GroupNotFoundException() {
        super();
    }

    public GroupNotFoundException(String s) {
        super(s);
    }
}
