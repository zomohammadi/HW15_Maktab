package menu;

import entity.Employee;
import entity.Student;
import entity.Teacher;
import entity.User;
import enumration.TeacherType;
import exception.StudentExceptions;
import service.EmployeeService;
import service.StudentService;
import service.TeacherService;
import util.ApplicationContext;

import java.util.Objects;
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
                        11. Delete Student
                        12. Update Student
                        13. Find ALl Student
                        14. Exit
                    """);
            System.out.print("Option: ");
            String stringOption = input.nextLine();
            if (stringOption == null || stringOption.isEmpty()) {
                System.out.println("Input can not be null or empty");
                showEmployeeMenu(token);
                break;
            }
            try {
                Integer option = Integer.parseInt(stringOption);
                switch (option) {
                    case 1 -> addEmployee(input);
                    case 2 -> deleteEmployee(input);
                    case 3 -> updateEmployee(input);
                    case 4 -> findAllEmployee();
                    case 5 -> showPaySlipEmployee(token);
                    case 6 -> addTeacher(input);
                    case 7 -> deleteTeacher(input);
                    case 8 -> updateTeacher(input);
                    case 9 -> findAllTeacher();
                    case 10 -> addStudent(input);
                    case 11 -> deleteStudent(input);
                    case 12 -> updateStudent(input);
                    case 13 -> findAllStudent();
                    case 14 -> condition = false;
                    default -> System.out.println("Wrong option!");
                }

            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("Wrong option!");
                }
            }
        }
    }

    private void deleteStudent(Scanner input) {
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
        Student student = studentService.findByUserNameAndPassword(username, password);
        if (student != null) {
            studentService.delete(student.getId());
            System.out.println("Done! ");
        } else System.out.println("the student with username and password not found!");
    }

    private void updateStudent(Scanner input) {
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
        Student student = studentService.findByUserNameAndPassword(username, password);
        if (student == null) {
            System.out.println("not student find! ");
            return;
        }

        //---------------
        System.out.print("Enter the FirstName: ");
        String firstName = input.nextLine();
        if (!firstName.isEmpty()) {
            if (!fillInputString_v2(firstName)) {
                return;
            } else {
                student.setFirstName(firstName);
            }
        }
        System.out.print("Enter the LastName: ");
        String lastName = input.nextLine();
        if (!lastName.isEmpty()) {
            if (!fillInputString_v2(lastName)) {
                return;
            } else {
                student.setLastName(lastName);
            }
        }

        System.out.print("Enter the National Code (10 digit): ");
        String nationalCode = input.nextLine();
        if (!nationalCode.isEmpty()) {
            if (!fillInputNumbers_v2(nationalCode, 10)) {
                return;
            } else {
                student.setNationalCode(nationalCode);
            }
        }
        System.out.print("Enter the MobileNumber (11 digit): ");
        String mobileNumber = input.nextLine();
        if (!mobileNumber.isEmpty()) {
            if (!fillInputNumbers_v2(mobileNumber, 11)) {
                return;
            } else {
                student.setMobileNumber(mobileNumber);
            }
        }
        System.out.print("Enter the student Code (5 digit): ");
        String studentCode = input.nextLine();
        if (!studentCode.isEmpty()) {
            if (!fillInputNumbers_v2(studentCode, 5)) {
                return;
            } else {
                student.setStudentCode(studentCode);
            }
        }

        studentService.update(student);
        System.out.println("Updated!");
    }

    private void findAllStudent() {
        studentService.findAll().forEach(System.out::println);
    }

    private void findAllTeacher() {
        teacherService.findAll().forEach(System.out::println);
    }

    private void updateTeacher(Scanner input) {
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
        Teacher teacher = teacherService.findByUserNameAndPassword(username, password);
        if (teacher == null) {
            System.out.println("not teacher find! ");
            return;
        }

        //---------------
        System.out.print("Enter the FirstName: ");
        String firstName = input.nextLine();
        if (!firstName.isEmpty()) {
            if (!fillInputString_v2(firstName)) {
                return;
            } else {
                teacher.setFirstName(firstName);
            }
        }
        System.out.print("Enter the LastName: ");
        String lastName = input.nextLine();
        if (!lastName.isEmpty()) {
            if (!fillInputString_v2(lastName)) {
                return;
            } else {
                teacher.setLastName(lastName);
            }
        }

        System.out.print("Enter the National Code (10 digit): ");
        String nationalCode = input.nextLine();
        if (!nationalCode.isEmpty()) {
            if (!fillInputNumbers_v2(nationalCode, 10)) {
                return;
            } else {
                teacher.setNationalCode(nationalCode);
            }
        }
        System.out.print("Enter the MobileNumber (11 digit): ");
        String mobileNumber = input.nextLine();
        if (!mobileNumber.isEmpty()) {
            if (!fillInputNumbers_v2(mobileNumber, 11)) {
                return;
            } else {
                teacher.setMobileNumber(mobileNumber);
            }
        }
        System.out.print("Enter the Teacher Code (5 digit): ");
        String teacherCode = input.nextLine();
        if (!teacherCode.isEmpty()) {
            if (!fillInputNumbers_v2(teacherCode, 5)) {
                return;
            } else {
                teacher.setTeacherCode(teacherCode);
            }
        }

        teacherService.update(teacher);
        System.out.println("Updated!");
    }

    private void deleteTeacher(Scanner input) {
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
        Teacher teacher = teacherService.findByUserNameAndPassword(username, password);
        if (teacher != null) {
            teacherService.delete(teacher.getId());
            System.out.println("Done! ");
        } else System.out.println("the teacher with username and password not found!");

    }

    private void addStudent(Scanner input) {
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
        System.out.print("Enter the Student Code (5 digit): ");
        String studentCode = input.nextLine();
        if (!fillInputNumbers(studentCode, 5)) {
            return;
        }
        System.out.print("Enter the Entry Year (4 digit): ");
        String enteringYear = input.nextLine();
        if (!fillInputNumbers(enteringYear, 4)) {
            return;
        }
        Student student = Student.builder().firstName(firstName).lastName(lastName).nationalCode(nationalCode)
                .mobileNumber(mobileNumber).username(username).password(password)
                .studentCode(studentCode).enteringYear(Integer.valueOf(enteringYear)).build();


        try {
            ApplicationContext.getInstance().getStudentService().save(student);
        } catch (Exception e) {
            if (e instanceof StudentExceptions.StudentInsertSqlException) {
                System.out.println(" student not save please do again");
            }
        }
        System.out.println("Done!");
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
        System.out.print("Enter the teacherType (contractual->Enter 0 digit or faculty_member->Enter 1 digit): ");
        String teacherType = input.nextLine();
        if (Objects.equals(teacherType, "0")) {
            teacherType = "CONTRACTUAL";
        } else if (Objects.equals(teacherType, "1")) {
            teacherType = "FACULTY_MEMBER";
        } else {
            System.out.println("Enter the correct TeacherType(CONTRACTUAL or FACULTY_MEMBER) ");
            return;
        }
        String baseSalary = "";
        if (teacherType.equals("CONTRACTUAL")) {
            baseSalary = "0";
        } else {
            System.out.print("Enter the Salary(min: 20000000.0, max: 60000000.0 =>  ");
            baseSalary = input.nextLine();
            if (!fillInputSalary(baseSalary, 20000000.0, 60000000.0)) {
                return;
            }
        }
        Teacher teacher = Teacher.builder().firstName(firstName).lastName(lastName).nationalCode(nationalCode)
                .mobileNumber(mobileNumber).username(username).password(password)
                .teacherCode(teacherCode).teacherType(TeacherType.valueOf(teacherType))
                .baseSalary(Double.valueOf(baseSalary)).build();

        teacherService.save(teacher);
        System.out.println("Don!");
    }

    private void showPaySlipEmployee(User token) {
        System.out.println(token);
    }

    private void findAllEmployee() {
        employeeService.findAll().forEach(System.out::println);
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
        Employee employee = employeeService.findByUserNameAndPassword(username, password);
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
        employeeService.update(employee);
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
        Employee employee = employeeService.findByUserNameAndPassword(username, password);
        if (employee != null) {
            employeeService.delete(employee.getId());
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

        System.out.print("Enter the Salary (min => 10000000.0, mx=> 50000000.0 ): ");
        String salary = input.nextLine();
        if (!fillInputSalary(salary, 10000000.0, 50000000.0)) {
            return;
        }

        Employee employee = Employee.builder().firstName(firstName).lastName(lastName)
                .username(username).password(password)
                .nationalCode(nationalCode).mobileNumber(mobileNumber)
                .PersonnelCode(personalCode).salary(Double.valueOf(salary)).build();


        employeeService.save(employee);
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
        if (chars[0] == ' ') {
            System.out.println("can not start with space");
            return false;
        }
        for (char c : chars) {
            if ((int) c != 32) {
                if (!Character.isLetter(c)) {
                    System.out.println("Input must contain only letters between (a-z) or (A-Z)");
                    return false;
                }
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

   /* public static void main(String[] args) {
        String s = "1.1";

        try {
            System.out.println(NumberUtils.isCreatable(s));
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.out.println(" eshtebahe");
            }
        }
    }*/
}
