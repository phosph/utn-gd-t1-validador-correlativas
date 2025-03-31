package edu.correlativas.app.exceptions;

import edu.correlativas.app.Course;
import edu.correlativas.app.Student;

public class StudentAlreadyHasApprovedCourseException extends Exception {

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    Student student;
    Course course;

    public StudentAlreadyHasApprovedCourseException(Student student, Course course) {
        super("Student already approved the Course");
        this.student = student;
        this.course = course;
    }
}
