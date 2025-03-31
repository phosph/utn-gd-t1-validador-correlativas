package edu.correlativas.app;

import java.util.ArrayList;

public class Student {
    ArrayList<Course> courses;

    public Student() {
        courses = new ArrayList<>();
    }

    void addApprovedCourse(Course course) {
      courses.add(course);
    }

    boolean hasApprovedCourse(Course course) {
        return courses.contains(course);
    }
}