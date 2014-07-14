package com.softserveinc.ita.utils.error;

import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

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
    public @ResponseBody
        ExceptionJSONInfo handleApplicantException(EntityException exception, HttpServletResponse response){
        int responseStatus = exception.getClass().getAnnotation(ResponseStatus.class).value().value(); //get response status of the exception class
        String exceptionReason = exception.getClass().getAnnotation(ResponseStatus.class).reason();  // get reason of the exception class
        ExceptionJSONInfo exceptionInfo = new ExceptionJSONInfo();
        exceptionInfo.setReason(exceptionReason);
        try {
            response.sendError(responseStatus);   //send http status code
        }
        catch (IOException ex){
            logger.info("IOException while sending responseStatus");
            ex.printStackTrace();
        }
        return exceptionInfo;
    }
}