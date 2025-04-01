package edu.correlativas.app;

import edu.correlativas.app.exceptions.InvalidCourseListException;
import edu.correlativas.app.exceptions.StudentAlreadyHasApprovedCourseException;
import java.util.List;

public class Inscription {

    Student student;
    List<Course> courses;

    public Inscription(Student student, List<Course> courses) throws InvalidCourseListException {
        if (courses.isEmpty()) throw new InvalidCourseListException("course list shouldn't be empty", courses);

        this.student = student;
        this.courses = courses;
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