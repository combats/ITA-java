package com.softserveinc.ita.entity.exceptions;

public class ExceptionJSONInfo {
    private String reason;
    public String getReason() {
        return reason;
    }
    public void setReason(String message) {
        this.reason = message;
    }

    public ExceptionJSONInfo(String reason) {
        this.reason = reason;
    }

    public ExceptionJSONInfo() {
    }
}
