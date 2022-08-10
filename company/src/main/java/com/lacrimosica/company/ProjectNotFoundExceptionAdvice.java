package com.lacrimosica.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProjectNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)


    String projectNotFoundHandler(ProjectNotFoundException ex) {
        return ex.getMessage();
    }

}
