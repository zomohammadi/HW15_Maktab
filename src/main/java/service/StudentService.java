package service;

import entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);
    void update(Student student);
    void delete(Long id);
    Student findById(Long id);
    List<Student> findAll();
    Student findByUserNameAndPassword(String userName, String password);
    Student findByCode(String code);

}
