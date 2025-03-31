package edu.correlativas.app;

import java.util.List;

public class Course {
    String id;
    List<Course> correlatives;

    public Course(String id) {
        this(id, List.of());
    }

    public Course(String id, List<Course> correlatives) {
        this.correlatives = correlatives;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    boolean studentHasRequirements(Student student) {
        return correlatives.stream().allMatch(student::hasApprovedCourse);
    }
}