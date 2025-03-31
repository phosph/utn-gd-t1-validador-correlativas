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
        var emptyList = new ArrayList<Course>();
        var student = new Student();

        InvalidCourseListException exception = assertThrows(InvalidCourseListException.class, () -> new Inscription(student, emptyList), "new Inscription with empty list should throws");

        assertEquals(exception.getCourseList(), emptyList, "exception provides the list that cause the error");
    }

    @Test
    public void coursesWithoutCorrelatives() throws InvalidCourseListException, StudentAlreadyHasApprovedCourseException {
        var student = new Student();
        var courses = IntStream.range(0,5).mapToObj((i) -> new Course(UUID.randomUUID().toString())).toList();

        var inscription = new Inscription(student, courses);

        assertTrue(inscription.approved());
    }

    @Test
    public void coursesWithCorrelatives() throws InvalidCourseListException, StudentAlreadyHasApprovedCourseException {
        var student = new Student();

        var unmatchedCorrelative = new Course(UUID.randomUUID().toString());

        var course = new Course(
                UUID.randomUUID().toString(),
                List.of(new Course[]{unmatchedCorrelative})
        );

        var inscription = new Inscription(
                student,
                List.of(new Course[]{course})
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
                List.of(new Course[]{matchedCorrelative})
        );

        var inscription = new Inscription(
                student,
                List.of(new Course[]{course})
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
                List.of(new Course[]{ approvedCourse })
        );

        StudentAlreadyHasApprovedCourseException exception = assertThrows(StudentAlreadyHasApprovedCourseException.class, inscription::approved);

        assertEquals(exception.getCourse(), approvedCourse);
        assertEquals(exception.getStudent(), student);

    }

}
