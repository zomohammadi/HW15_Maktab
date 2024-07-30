package service;

import entity.Teacher;

import java.util.List;

public interface TeacherService {
    void save(Teacher teacher);
    void update(Teacher teacher);
    void delete(Teacher teacher);
    Teacher findById(Long id);
    List<Teacher> findAll();
    Teacher findByUserNameAndPassword(String userName, String password);

}
