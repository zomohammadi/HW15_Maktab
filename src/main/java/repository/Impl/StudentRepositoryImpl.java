package repository.Impl;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void save(Student student) {
        if (student != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Student student) {
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    public void delete(Student student) {
        entityManager.getTransaction().begin();
        //findById(student.getId());
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAll() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        return entityManager.createQuery(query).getResultList();
        // TypedQuery<Student> query1 = entityManager.createQuery(query);
    }

    public Student findByUserNameAndPassword(String userName, String password) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userName != null) {
            predicates.add(criteriaBuilder.like(studentRoot.get("username"), userName));
        }
        if (password != null) {
            predicates.add(criteriaBuilder.like(studentRoot.get("password"), password));
        }
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        return entityManager.createQuery(query).getSingleResult();

    }
}

