
package repository.Impl;

import entity.Employee;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.EmployeeRepository;

public class EmployeeRepositoryImpl extends BaseEntityRepositoryImpl<Employee> implements EmployeeRepository {
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public Employee findByCode(String code) {

        TypedQuery<Employee> query = getEntityManager()
                .createQuery("""
                                                                    select p from  Employee 
                                                                     p where p.code = ?1
                                                                    """, Employee.class);
        return query.getSingleResult();
    }
}

  /*
    private final EntityManager entityManager;
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
    }*/


