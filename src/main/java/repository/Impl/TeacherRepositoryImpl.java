package repository.Impl;

import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    private final EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public TeacherRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void save(Teacher teacher) {
        if (teacher != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(teacher);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
    }

    public void delete(Teacher teacher) {
        entityManager.getTransaction().begin();
        //findById(teacher.getId());
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
    }

    public Teacher findById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> findAll() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = query.from(Teacher.class);
        return entityManager.createQuery(query).getResultList();
        // TypedQuery<Teacher> query1 = entityManager.createQuery(query);
    }

    public Teacher findByUserNameAndPassword(String userName, String password) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = query.from(Teacher.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userName != null) {
            predicates.add(criteriaBuilder.like(teacherRoot.get("username"), userName));
        }
        if (password != null) {
            predicates.add(criteriaBuilder.like(teacherRoot.get("password"), password));
        }
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        return entityManager.createQuery(query).getSingleResult();

    }
}

