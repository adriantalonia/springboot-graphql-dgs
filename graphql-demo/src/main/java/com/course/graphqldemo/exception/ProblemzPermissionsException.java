package com.course.graphqldemo.exception;

public class ProblemzPermissionsException extends RuntimeException {
    public ProblemzPermissionsException() {
        super("YOu are not allowed to access this operation");
    }
}
