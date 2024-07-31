
package repository.Impl;

import entity.Student;
import jakarta.persistence.EntityManager;
import repository.StudentRepository;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

}