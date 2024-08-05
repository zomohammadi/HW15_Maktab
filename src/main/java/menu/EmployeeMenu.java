package menu;

import entity.Employee;
import entity.User;
import enumration.TeacherType;
import service.EmployeeService;
import service.StudentService;
import service.TeacherService;
import util.ApplicationContext;

import java.util.Scanner;

public class EmployeeMenu {
    private final EmployeeService employeeService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public EmployeeMenu(EmployeeService employeeService, TeacherService teacherService, StudentService studentService) {
        this.employeeService = employeeService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public void showEmployeeMenu(User token) {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            System.out.println("""
                        Employee Operation Menu:
                        1. Add Employee
                        2. Delete Employee
                        3. Update Employee
                        4. Find ALl Employee
                        5. Show Pay Slip Employee
                        6. Add Teacher
                        7. Delete Teacher
                        8. Update Teacher
                        9. Find ALl Teacher
                        10. Add Student
                        11. Delete Employee
                        12. Update Employee
                        13. Find ALl Employee
                        14. Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> addEmployee(input);
                case 2 -> deleteEmployee(input);
                case 3 -> updateEmployee(input);
                case 4 -> findAllEmployee();
                case 5 -> showPaySlipEmployee(token);
                case 6 -> addTeacher(input);
                case 11 -> condition = false;
                default -> System.out.println("Wrong option!");
            }
        }
    }

    private void addTeacher(Scanner input) {
        System.out.print("Enter the FirstName: ");
        String firstName = input.nextLine();
        if (!fillInputString(firstName)) {
            return;
        }

        System.out.print("Enter the LastName: ");
        String lastName = input.nextLine();
        if (!fillInputString(lastName)) {
            return;
        }

        System.out.print("Enter the National Code (10 digit): ");
        String nationalCode = input.nextLine();
        if (!fillInputNumbers(nationalCode, 10)) {
            return;
        }

        System.out.print("Enter the MobileNumber (11 digit): ");
        String mobileNumber = input.nextLine();
        if (!fillInputNumbers(mobileNumber, 11)) {
            return;
        }

        System.out.print("Enter the Username: ");
        String username = input.nextLine();
        if (checkedNullInput(username)) {
            return;
        }
        System.out.print("Enter the Password: ");
        String password = input.nextLine();
        if (checkedNullInput(password)) {
            return;
        }
        System.out.print("Enter the Teacher Code (5 digit): ");
        String teacherCode = input.nextLine();
        if (!fillInputNumbers(teacherCode, 5)) {
            return;
        }
        String teacherType = input.nextLine();
        if (!teacherType.equals(TeacherType.CONTRACTUAL.name().toLowerCase()) || !teacherType.equals(TeacherType.FACULTY_MEMBER.name().toLowerCase())) {
            System.out.println("Enter the correct TeacherType(TeacherType or FACULTY_MEMBER) ");
            return;
        }
        String baseSalary = "";
        if (teacherType.equals(TeacherType.CONTRACTUAL.name().toLowerCase())) {
            baseSalary = "0";
        } else {
            System.out.print("Enter the Salary: ");
            baseSalary = input.nextLine();
            if (!fillInputSalary(baseSalary, 10000000.0, 50000000.0)) {
                return;
            }
        }

    }

    private void showPaySlipEmployee(User token) {
        System.out.println(token);
    }

    private void findAllEmployee() {
        ApplicationContext.getInstance().getEmployeeService().findAll().forEach(System.out::println);
    }

    private void updateEmployee(Scanner input) {
        System.out.print("Enter the Username: ");
        String username = input.nextLine();
        if (checkedNullInput(username)) {
            return;
        }
        System.out.print("Enter the Password: ");
        String password = input.nextLine();
        if (checkedNullInput(password)) {
            return;
        }
        Employee employee = ApplicationContext.getInstance().getEmployeeService().findByUserNameAndPassword(username, password);
        if (employee == null) {
            System.out.println("not employee find! ");
            return;
        }

        //---------------
        System.out.print("Enter the FirstName: ");
        String firstName = input.nextLine();
        if (!firstName.isEmpty()) {
            if (!fillInputString_v2(firstName)) {
                return;
            } else {
                employee.setFirstName(firstName);
            }
        }
        System.out.print("Enter the LastName: ");
        String lastName = input.nextLine();
        if (!lastName.isEmpty()) {
            if (!fillInputString_v2(lastName)) {
                return;
            } else {
                employee.setLastName(lastName);
            }
        }

        System.out.print("Enter the National Code (10 digit): ");
        String nationalCode = input.nextLine();
        if (!nationalCode.isEmpty()) {
            if (!fillInputNumbers_v2(nationalCode, 10)) {
                return;
            } else {
                employee.setNationalCode(nationalCode);
            }
        }
        System.out.print("Enter the MobileNumber (11 digit): ");
        String mobileNumber = input.nextLine();
        if (!mobileNumber.isEmpty()) {
            if (!fillInputNumbers_v2(mobileNumber, 11)) {
                return;
            } else {
                employee.setMobileNumber(mobileNumber);
            }
        }
        System.out.print("Enter the Personal Code (5 digit): ");
        String personalCode = input.nextLine();
        if (!personalCode.isEmpty()) {
            if (!fillInputNumbers_v2(personalCode, 5)) {
                return;
            } else {
                employee.setPersonnelCode(personalCode);
            }
        }

        System.out.print("Enter the Salary: ");
        String salary = input.nextLine();
        if (!salary.isEmpty()) {
            if (!fillInputSalary_v2(salary, 10000000.0, 50000000.0)) {
                return;
            } else {
                employee.setSalary(Double.valueOf(salary));
            }
        }
        ApplicationContext.getInstance().getEmployeeService().update(employee);
        System.out.println("Updated!");
    }

    private void deleteEmployee(Scanner input) {
        System.out.print("Enter the Username: ");
        String username = input.nextLine();
        if (checkedNullInput(username)) {
            return;
        }
        System.out.print("Enter the Password: ");
        String password = input.nextLine();
        if (checkedNullInput(password)) {
            return;
        }
        Employee employee = ApplicationContext.getInstance().getEmployeeService().findByUserNameAndPassword(username, password);
        if (employee != null) {
            ApplicationContext.getInstance().getEmployeeService().delete(employee.getId());
            System.out.println("Done! ");
        } else System.out.println("the employee with username and password not found!");

    }

    private void addEmployee(Scanner input) {
        //a-z --> 97 ta 122 // A--Z --> 65 ta 90
        System.out.print("Enter the FirstName: ");
        String firstName = input.nextLine();
        if (!fillInputString(firstName)) {
            return;
        }

        System.out.print("Enter the LastName: ");
        String lastName = input.nextLine();
        if (!fillInputString(lastName)) {
            return;
        }

        System.out.print("Enter the National Code (10 digit): ");
        String nationalCode = input.nextLine();
        if (!fillInputNumbers(nationalCode, 10)) {
            return;
        }

        System.out.print("Enter the MobileNumber (11 digit): ");
        String mobileNumber = input.nextLine();
        if (!fillInputNumbers(mobileNumber, 11)) {
            return;
        }

        System.out.print("Enter the Username: ");
        String username = input.nextLine();
        if (checkedNullInput(username)) {
            return;
        }
        System.out.print("Enter the Password: ");
        String password = input.nextLine();
        if (checkedNullInput(password)) {
            return;
        }
        System.out.print("Enter the Personal Code (5 digit): ");
        String personalCode = input.nextLine();
        if (!fillInputNumbers(personalCode, 5)) {
            return;
        }

        System.out.print("Enter the Salary: ");
        String salary = input.nextLine();
        if (!fillInputSalary(salary, 10000000.0, 50000000.0)) {
            return;
        }

        Employee employee = Employee.builder().firstName(firstName).lastName(lastName)
                .username(username).password(password)
                .nationalCode(nationalCode).mobileNumber(mobileNumber)
                .PersonnelCode(personalCode).salary(Double.valueOf(salary)).build();


        ApplicationContext.getInstance().getEmployeeService().save(employee);
        System.out.println("Don!");
    }


    private boolean fillInputSalary(String input, Double minSalary, Double maxSalary) {
        if (checkedNullInput(input)) return false;
        return fillInputSalary_v2(input, minSalary, maxSalary);
    }

    private boolean fillInputSalary_v2(String input, Double minSalary, Double maxSalary) {
        if (Double.parseDouble(input) < minSalary) {
            System.out.println("Minimum Salary is : " + minSalary);
            return false;
        }
        if (Double.parseDouble(input) > maxSalary) {
            System.out.println("Maximum Salary that assign, is : " + maxSalary);
            return false;
        }
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                System.out.println("input must contain only numbers between (0-9)");
                return false;
            }
        }
        return true;
    }

    private boolean fillInputNumbers_v2(String input, int digit) {

        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                System.out.println("input must contain only numbers between (0-9)");
                return false;
            }
        }
        return true;
    }

    private boolean fillInputNumbers(String input, int digit) {
        if (checkedNullInput(input)) return false;
        if (input.length() != digit) {
            System.out.println("input must be " + digit + " digit number");
            return false;
        }
        return fillInputNumbers_v2(input, digit);
    }


    private boolean fillInputString(String input) {
        if (checkedNullInput(input)) return false;
        return fillInputString_v2(input);
    }

    private boolean fillInputString_v2(String input) {
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                System.out.println("Input must contain only letters between (a-z) or (A-Z)");
                return false;
            }
        }
        return true;
    }

    private boolean checkedNullInput(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Input can not be null or empty");
            return true;
        }
        return false;
    }
}
