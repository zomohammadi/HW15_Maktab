package service.Impl;

import entity.Student;
import exception.StudentExceptions;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import repository.StudentRepository;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final BaseEntityRepository<Student> studentRepository;
    private final StudentRepository studentRepository1;

    public StudentServiceImpl(BaseEntityRepository<Student> studentRepository, StudentRepository studentRepository1) {
        this.studentRepository = studentRepository;
        this.studentRepository1 = studentRepository1;
    }

    @Override
    public void save(Student student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                System.out.println("not Save" + e.getMessage());
                throw new StudentExceptions.StudentInsertSqlException(e.getMessage(), e);
            } else {
                System.out.println("not Save" + e.getMessage());
            }

        }
    }

    @Override
    public void update(Student student) {
        try {
            studentRepository.update(student);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Student student = findById(id);
            studentRepository.delete(student);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findById(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (NoResultException e) {
            System.out.println("No result found " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            return studentRepository.findAll();
        } catch (NoResultException e) {
            System.out.println("No result found " + e.getMessage());
            return null;
        }
    }

    @Override
    public Student findByUserNameAndPassword(String userName, String password) {
        try {

            return studentRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found " + e.getMessage());
            return null;
        }
    }

    @Override
    public Student findByCode(String code) {
        try {
            return studentRepository1.findByCode(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
