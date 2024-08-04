package service.Impl;

import entity.Course;
import repository.BaseEntityRepository;
import repository.CourseRepository;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private BaseEntityRepository<Course> baseEntityRepositoryCourse;

    public CourseServiceImpl(CourseRepository courseRepository, repository.BaseEntityRepository<Course> baseEntityRepositoryCourse) {
        this.courseRepository = courseRepository;
        this.baseEntityRepositoryCourse = baseEntityRepositoryCourse;
    }

   /*  public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }*/

    @Override
    public List<Course> getCourseInCurrentTerm() {
        return courseRepository.getCourseInCurrentTerm();
    }

    @Override
    public void update(Course course) {
        baseEntityRepositoryCourse.update(course);
    }

    @Override
    public Course findById(Long id) {
        return baseEntityRepositoryCourse.findById(id);
    }


}
