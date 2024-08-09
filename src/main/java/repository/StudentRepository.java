package repository;

import entity.Student;

public interface StudentRepository {
    Student findByCode(String code);
}
