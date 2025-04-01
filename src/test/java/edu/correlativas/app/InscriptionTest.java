package edu.correlativas.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.correlativas.app.exceptions.InvalidCourseListException;
import edu.correlativas.app.exceptions.StudentAlreadyHasApprovedCourseException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;


public class InscriptionTest {

    @Test
    public void emptyListException() {
        var student = new Student();

        InvalidCourseListException exception = assertThrows(InvalidCourseListException.class, () -> new Inscription(student), "new Inscription with empty list should throws");
    }

    @Test
    public void coursesWithoutCorrelatives() throws InvalidCourseListException, StudentAlreadyHasApprovedCourseException {
        var student = new Student();

        var inscription = new Inscription(student,
                new Course(UUID.randomUUID().toString()),
                new Course(UUID.randomUUID().toString()),
                new Course(UUID.randomUUID().toString()),
                new Course(UUID.randomUUID().toString()),
                new Course(UUID.randomUUID().toString())
        );

        assertTrue(inscription.approved());
    }

    @Test
    public void coursesWithCorrelatives() throws InvalidCourseListException, StudentAlreadyHasApprovedCourseException {
        var student = new Student();

        var unmatchedCorrelative = new Course(UUID.randomUUID().toString());

        var course = new Course(
                UUID.randomUUID().toString(),
                unmatchedCorrelative
        );

        var inscription = new Inscription(
                student,
                course
        );

        assertFalse(inscription.approved());
    }

    @Test
    public void coursesWithCorrelativesSuccess() throws InvalidCourseListException, StudentAlreadyHasApprovedCourseException {
        var student = new Student();

        var matchedCorrelative = new Course(UUID.randomUUID().toString());

        student.addApprovedCourse(matchedCorrelative);

        var course = new Course(
                UUID.randomUUID().toString(),
                matchedCorrelative
        );

        var inscription = new Inscription(
                student,
                course
        );

        assertTrue(inscription.approved());
    }

    @Test
    public void studentAlreadyApprovedCourse() throws InvalidCourseListException {
        var student = new Student();

        var approvedCourse = new Course(UUID.randomUUID().toString());

        student.addApprovedCourse(approvedCourse);


        var inscription = new Inscription(
                student,
                approvedCourse
        );

        StudentAlreadyHasApprovedCourseException exception = assertThrows(StudentAlreadyHasApprovedCourseException.class, inscription::approved);

        assertEquals(exception.getCourse(), approvedCourse);
        assertEquals(exception.getStudent(), student);

    }

}
