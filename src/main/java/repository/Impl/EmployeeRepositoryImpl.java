package repository.Impl;

import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void save(Employee employee) {
        if (employee != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        }
    }

    public void update(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
    }

    public void delete(Employee employee) {
        entityManager.getTransaction().begin();
        //findById(employee.getId());
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        return entityManager.createQuery(query).getResultList();
        // TypedQuery<Employee> query1 = entityManager.createQuery(query);
    }

    public Employee findByUserNameAndPassword(String userName, String password) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userName != null) {
            predicates.add(criteriaBuilder.like(employeeRoot.get("username"), userName));
        }
        if (password != null) {
            predicates.add(criteriaBuilder.like(employeeRoot.get("password"), password));
        }
        if (!predicates.isEmpty()) {
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        return entityManager.createQuery(query).getSingleResult();

    }
}

