package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.EmployeeRepository;
import repository.Impl.EmployeeRepositoryImpl;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;

public class ApplicationContext {

    private EntityManagerFactory enf;
    private EntityManager em;

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public ApplicationContext() {
        this.em = getEntityManager();
        employeeRepository = new EmployeeRepositoryImpl(em);

        employeeService = new EmployeeServiceImpl(employeeRepository);

    }

    private static ApplicationContext applicationContext;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }


    public EntityManagerFactory getEntityManagerFactory() {
        if (enf == null) {
            enf = Persistence.createEntityManagerFactory("default");
        }
        return enf;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}