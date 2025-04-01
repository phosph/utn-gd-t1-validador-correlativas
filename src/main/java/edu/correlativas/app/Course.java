package edu.correlativas.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {
    String id;
    ArrayList<Course> correlatives = new ArrayList<>();


    public Course(String id, Course... correlatives) {
        Collections.addAll(this.correlatives, correlatives);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    boolean studentHasRequirements(Student student) {
        return correlatives.stream().allMatch(student::hasApprovedCourse);
    }
}