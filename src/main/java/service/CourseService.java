package service;

import entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourseInCurrentTerm();
    void update(Course course);
    Course findById(Long id);
}
