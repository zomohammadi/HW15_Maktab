package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.EmployeeRepository;
import repository.Impl.EmployeeRepositoryImpl;
import repository.Impl.StudentRepositoryImpl;
import repository.Impl.TeacherRepositoryImpl;
import repository.StudentRepository;
import repository.TeacherRepository;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;
import service.Impl.StudentServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.StudentService;
import service.TeacherService;

public class ApplicationContext {

    private EntityManagerFactory enf;
    private EntityManager em;

    private final EmployeeService employeeService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public ApplicationContext() {
        this.em = getEntityManager();
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(em);
        TeacherRepository teacherRepository = new TeacherRepositoryImpl(em);
        StudentRepository studentRepository = new StudentRepositoryImpl(em);

        employeeService = new EmployeeServiceImpl(employeeRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);
        studentService = new StudentServiceImpl(studentRepository);

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

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public StudentService getStudentService() {
        return studentService;
    }
}