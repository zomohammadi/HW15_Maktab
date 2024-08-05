package repository.Impl;

import entity.BaseEntity;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import repository.BaseEntityRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<T extends BaseEntity> implements BaseEntityRepository<T> {
    private final EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public BaseEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T t) {
        if (t != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        }
    }

    public void update(T t) {
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
    }

    public void delete(T t) {
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }

    public List<T> findAll() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> employeeRoot = query.from(getEntityClass());
        return entityManager.createQuery(query).getResultList();
        // TypedQuery<Employee> query1 = entityManager.createQuery(query);
    }

    public T findById(Long id) {
        return entityManager.find(getEntityClass(), id);
    }

    public T findByUserNameAndPassword(String userName, String password) {
        try {
            criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
            Root<T> employeeRoot = query.from(getEntityClass());
            List<Predicate> predicates = new ArrayList<>();
            if (userName != null) {
                predicates.add(criteriaBuilder.like(employeeRoot.get(User.USERNAME), userName));
            }
            if (password != null) {
                predicates.add(criteriaBuilder.like(employeeRoot.get(User.PASSWORD), password));
            }
            if (!predicates.isEmpty()) {
                query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            }
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            System.out.println("");
            return null;
        }

    }

    public abstract Class<T> getEntityClass();

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
