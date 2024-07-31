package util;

import entity.Employee;
import entity.Lesson;
import entity.Student;
import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.BaseEntityRepository;
import repository.Impl.EmployeeRepositoryImpl;
import repository.Impl.LessonRepositoryImpl;
import repository.Impl.StudentRepositoryImpl;
import repository.Impl.TeacherRepositoryImpl;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;
import service.Impl.LessonServiceImpl;
import service.Impl.StudentServiceImpl;
import service.Impl.TeacherServiceImpl;
import service.LessonService;
import service.StudentService;
import service.TeacherService;

public class ApplicationContext {

    private EntityManagerFactory enf;
    private EntityManager em;

    private final EmployeeService employeeService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final LessonService lessonService;

    public ApplicationContext() {
        this.em = getEntityManager();
      //  EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(em);

       // BaseEntityRepository<Employee> employeeRepository = new BaseEntityRepositoryImpl<>(em);

        BaseEntityRepository<Employee> employeeRepository = new EmployeeRepositoryImpl(em);
        BaseEntityRepository<Teacher> teacherRepository = new TeacherRepositoryImpl(em);
        BaseEntityRepository<Student> studentRepository = new StudentRepositoryImpl(em);
        BaseEntityRepository<Lesson> lessonRepository = new LessonRepositoryImpl(em);

        employeeService = new EmployeeServiceImpl(employeeRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);
        studentService = new StudentServiceImpl(studentRepository);
        lessonService = new LessonServiceImpl(lessonRepository);

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

    public LessonService getLessonService() {
        return lessonService;
    }
}