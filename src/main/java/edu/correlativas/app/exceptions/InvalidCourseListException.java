package edu.correlativas.app.exceptions;

import edu.correlativas.app.Course;

import java.util.List;

public class InvalidCourseListException extends Exception {
    public List<Course> getCourseList() {
        return courseList;
    }

    List<Course> courseList;

    public InvalidCourseListException(String message, List<Course> courseList) {
        super(message);
        this.courseList = courseList;
    }
}
