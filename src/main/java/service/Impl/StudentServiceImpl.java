package service.Impl;

import entity.Student;
import jakarta.persistence.NoResultException;
import repository.StudentRepository;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepository employeeRepository;

    public StudentServiceImpl(StudentRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Student employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void update(Student employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(Student employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Student findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Student findByUserNameAndPassword(String userName, String password) {
        try {

            return employeeRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found");
            return null;
        }
    }
}
