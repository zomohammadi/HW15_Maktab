
package repository.Impl;

import entity.Teacher;
import jakarta.persistence.EntityManager;
import repository.TeacherRepository;

public class TeacherRepositoryImpl extends BaseEntityRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}