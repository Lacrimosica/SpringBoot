package com.example.company;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

public class ProjectNotFoundAdvice {

    @ResponseBody

    @ExceptionHandler(ProjectNotFoundException.class)

    @ResponseStatus(HttpStatus.NOT_FOUND)

    String projectNotFoundHandler(ProjectNotFoundException ex) {
        return ex.getMessage();
    }

}
