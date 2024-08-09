
package repository.Impl;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.StudentRepository;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student findByCode(String code) {

        TypedQuery<Student> query = getEntityManager()
                .createQuery("""
                        select p from  Student 
                         p where p.code = ?1
                        """, Student.class);
        query.setParameter(1, code);
        return query.getSingleResult();
    }

}