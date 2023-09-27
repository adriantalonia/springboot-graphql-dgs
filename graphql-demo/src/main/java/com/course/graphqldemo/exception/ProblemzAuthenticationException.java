package com.course.graphqldemo.exception;

public class ProblemzAuthenticationException extends RuntimeException {
    public ProblemzAuthenticationException() {
        super("Invalid credential");
    }
}
