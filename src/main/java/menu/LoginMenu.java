package menu;

import entity.Employee;
import entity.User;
import service.EmployeeService;
import service.StudentService;
import service.TeacherService;

import java.util.Scanner;

public class LoginMenu {
    private User token = null;
    private final EmployeeService employeeService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public LoginMenu(EmployeeService employeeService, TeacherService teacherService, StudentService studentService) {
        this.employeeService = employeeService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public void showLoginMenu() {
        Scanner input = new Scanner(System.in);
        boolean continueRunning = true;
        while (continueRunning) {
            System.out.println("""
                        Menu:
                        1. Login
                        2. Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> continueRunning = !login(input);
                case 2 -> {
                    token = null;
                    continueRunning = false;  // Exit the application
                }
                default -> System.out.println("Wrong option!");
            }
        }
    }

    private boolean login(Scanner input) {
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();

        token = employeeService.findByUserNameAndPassword(username, password);
        if (username.equals("admin") && password.equals("admin123") && token == null) {

            Employee employee = Employee.builder().username(username).password(password).build();
            employeeService.save(employee);
            token = employeeService.findByUserNameAndPassword(username, password);
        }
        if (token == null) {
            token = teacherService.findByUserNameAndPassword(username, password);
        }
        if (token == null) {
            token = studentService.findByUserNameAndPassword(username, password);
        }
        if (token == null) {
            System.out.println("User or password is incorrect");
            return false;
        }
        return true;
    }

    public User getToken() {
        return token;
    }

}
