package service.Impl;

import entity.Student;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private BaseEntityRepository<Student> studentRepository;

    public StudentServiceImpl(BaseEntityRepository<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(Student employee) {
        studentRepository.save(employee);
    }

    @Override
    public void update(Student employee) {
        studentRepository.update(employee);
    }

    @Override
    public void delete(Student employee) {
        studentRepository.delete(employee);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByUserNameAndPassword(String userName, String password) {
        try {

            return studentRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found");
            return null;
        }
    }
}
