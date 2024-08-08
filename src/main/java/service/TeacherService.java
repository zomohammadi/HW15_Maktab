package service;

import entity.Teacher;

import java.util.List;

public interface TeacherService {
    void save(Teacher teacher);

    void update(Teacher teacher);

    void delete(Long id);

    Teacher findById(Long id);

    List<Teacher> findAll();

    Teacher findByUserNameAndPassword(String userName, String password);

    List<Long> getCourseTaughtByTeacher(Long teacherId, Long termId);

    List<Integer> getUnitsThatTaughtByTeacher(List<Long> course_id);

    Integer getSumUnits(List<Integer> units);
}
