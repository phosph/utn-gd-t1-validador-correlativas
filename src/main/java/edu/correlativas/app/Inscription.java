package edu.correlativas.app;

import edu.correlativas.app.exceptions.InvalidCourseListException;
import edu.correlativas.app.exceptions.StudentAlreadyHasApprovedCourseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inscription {

    Student student;
    ArrayList<Course> courses = new ArrayList<>();

    public Inscription(Student student, Course... courses) throws InvalidCourseListException {
        if (courses.length == 0) throw new InvalidCourseListException();

        this.student = student;
        Collections.addAll(this.courses, courses);
    }

    private void assertAlreadyApprovedCourse() throws StudentAlreadyHasApprovedCourseException {
        for (var course : courses) {
            if (student.hasApprovedCourse(course)) {
                throw new StudentAlreadyHasApprovedCourseException(student, course);
            }
        }
    }

    boolean approved() throws StudentAlreadyHasApprovedCourseException {
        assertAlreadyApprovedCourse();
        return courses.stream().allMatch(course -> course.studentHasRequirements(student));
    }
}