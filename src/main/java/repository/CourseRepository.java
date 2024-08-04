package repository;

import entity.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> getCourseInCurrentTerm();
}
