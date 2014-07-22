package com.softserveinc.ita.utils.error;

import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = Logger.getLogger(getClass());
    private StringBuilder logMessage;

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<ObjectError> bodyOfResponse = ex.getBindingResult().getAllErrors();
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * @param exception -> Controller specific exception.
     * @param response  -> Http response code.
     * @return ExceptionJSONInfo -> Reason of exception transformed into Json format.
     */
    @ExceptionHandler(EntityException.class)
    public @ResponseBody ExceptionJSONInfo handleApplicantException(EntityException exception, HttpServletResponse response, HttpServletRequest request){
        logMessage = new StringBuilder();
        int responseStatus = exception.getClass().getAnnotation(ResponseStatus.class).value().value(); //get response status of the exception class
        String exceptionReason = exception.getClass().getAnnotation(ResponseStatus.class).reason();  // get reason of the exception class
        ExceptionJSONInfo exceptionInfo = new ExceptionJSONInfo();
        if(exception.getMessage() == null) {
            exceptionInfo.setReason(exceptionReason);
        } else {
            exceptionInfo.setReason(exceptionReason + " " + exception.getMessage());
        }
        try {
            response.sendError(responseStatus);   //send http status code
        }
        catch (IOException ex){
            logger.info("IOException while sending responseStatus");
            ex.printStackTrace();
        }
        logMessage.append(" ********START_ERROR_SNIPPET********\n");
        logMessage.append(" Error during the request to the URL "+request.getRequestURI()+".\n");
        logMessage.append(" Reason is: " + exceptionInfo.getReason() + ".\n");
        logMessage.append(" HttpStatus is " + responseStatus+ ".\n");
        logMessage.append(" ********END_ERROR_SNIPPET********\n");
        logger.error(logMessage);
        return exceptionInfo;
    }
}