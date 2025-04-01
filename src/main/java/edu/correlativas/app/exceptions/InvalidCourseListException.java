package edu.correlativas.app.exceptions;

import edu.correlativas.app.Course;

import java.util.List;

public class InvalidCourseListException extends Exception {
    public InvalidCourseListException() {
        super("course list shouldn't be empty");
    }
}
