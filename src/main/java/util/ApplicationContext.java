package util;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menu.*;
import repository.BaseEntityRepository;
import repository.CourseRepository;
import repository.Impl.*;
import repository.SelectUnitRepository;
import service.*;
import service.Impl.*;

public class ApplicationContext {

    private EntityManagerFactory enf;
    private EntityManager em;

    private final EmployeeService employeeService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final LessonService lessonService;

    private final SelectUnitService selectUnitService;
    private final CourseService courseService;

    private final MainMenu mainMenu;
    private final LoginMenu loginMenu;
    private final ItemsMenu itemsMenu;

    private final EmployeeMenu employeeMenu;
    private final TeacherMenu teacherMenu;
    private final StudentMenu studentMenu;

    public ApplicationContext() {
        this.em = getEntityManager();
        //  EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(em);

        // BaseEntityRepository<Employee> employeeRepository = new BaseEntityRepositoryImpl<>(em);

        BaseEntityRepository<Employee> employeeRepository = new EmployeeRepositoryImpl(em);
        BaseEntityRepository<Teacher> teacherRepository = new TeacherRepositoryImpl(em);
        BaseEntityRepository<Student> studentRepository = new StudentRepositoryImpl(em);
        BaseEntityRepository<Lesson> lessonRepository = new LessonRepositoryImpl(em);

        SelectUnitRepository selectUnitRepository = new SelectUnitRepositoryImpl(em);
        CourseRepository courseRepository = new CourseRepositoryImpl(em);

        BaseEntityRepository<Course> baseEntityRepositoryCourse = new CourseRepositoryImpl(em);

        employeeService = new EmployeeServiceImpl(employeeRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);
        studentService = new StudentServiceImpl(studentRepository);
        lessonService = new LessonServiceImpl(lessonRepository);

        selectUnitService = new SelectUnitServiceImpl(selectUnitRepository);
        courseService = new CourseServiceImpl(courseRepository, baseEntityRepositoryCourse);

        //menu
        this.employeeMenu = new EmployeeMenu(this.employeeService, teacherService, studentService);
        this.teacherMenu = new TeacherMenu();
        this.studentMenu = new StudentMenu(this.studentService, this.selectUnitService, this.courseService);
        this.itemsMenu = new ItemsMenu(employeeMenu, teacherMenu, studentMenu);
        this.loginMenu = new LoginMenu(this.employeeService, teacherService, studentService);
        this.mainMenu = new MainMenu(this.loginMenu, this.itemsMenu);
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

    public SelectUnitService getSelectUnitService() {
        return selectUnitService;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public ItemsMenu getItemsMenu() {
        return itemsMenu;
    }

    public LoginMenu getLoginMenu() {
        return loginMenu;
    }

    public EmployeeMenu getEmployeeMenu() {
        return employeeMenu;
    }

    public TeacherMenu getTeacherMenu() {
        return teacherMenu;
    }

    public StudentMenu getStudentMenu() {
        return studentMenu;
    }
}