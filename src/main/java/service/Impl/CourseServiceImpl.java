package service.Impl;

import entity.Course;
import repository.BaseEntityRepository;
import repository.CourseRepository;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final BaseEntityRepository<Course> baseEntityRepositoryCourse;

    public CourseServiceImpl(CourseRepository courseRepository, repository.BaseEntityRepository<Course> baseEntityRepositoryCourse) {
        this.courseRepository = courseRepository;
        this.baseEntityRepositoryCourse = baseEntityRepositoryCourse;
    }

    @Override
    public List<Course> getCourseInCurrentTerm() {
        return courseRepository.getCourseInCurrentTerm();
    }

    @Override
    public void update(Course course) {
        try {
            baseEntityRepositoryCourse.update(course);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Course findById(Long id) {
        try {
            return baseEntityRepositoryCourse.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
