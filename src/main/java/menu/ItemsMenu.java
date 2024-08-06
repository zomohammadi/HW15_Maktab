package menu;

import entity.Employee;
import entity.Student;
import entity.Teacher;
import entity.User;

import java.util.Scanner;

public class ItemsMenu {
    private final EmployeeMenu employeeMenu;
    private final TeacherMenu teacherMenu;
    private final StudentMenu studentMenu;

    public ItemsMenu(EmployeeMenu employeeMenu, TeacherMenu teacherMenu, StudentMenu studentMenu) {
        this.employeeMenu = employeeMenu;
        this.teacherMenu = teacherMenu;
        this.studentMenu = studentMenu;
    }

    public void showItemMenu(User token) {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        itemLable:
        while (condition) {
            System.out.println("""
                        Items Menu:
                        1. Employee Operation
                        2. Teacher Operation
                        3. Student Operations
                        4. Exit
                    """);
            System.out.print("Option: ");
            int option = input.nextInt();
            input.nextLine();
            /*System.out.print("Option: ");
            String stringOption = input.nextLine();
            if (stringOption == null || stringOption.isEmpty()) {
                System.out.println("Input can not be null or empty");
                return;
            }
            char[] chars = stringOption.toCharArray();
            for (char c : chars) {
                if (!Character.isDigit(c)) {
                    System.out.println("input must contain only number between (0-9)");
                    return;
                }
            }
            // input.nextLine();
            int option = Integer.parseInt(stringOption);*/
            switch (option) {
                case 1 -> {
                    if (token instanceof Employee) {
                        employeeMenu.showEmployeeMenu(token);
                    } else
                        System.out.println("you are not authorised! your privilege is: " + token.getClass().getSimpleName());
                }
                case 2 -> {
                    if (token instanceof Teacher) {
                        // teacherMenu.showStudentMenu(token);
                    } else
                        System.out.println("you are not authorised! your privilege is: " + token.getClass().getSimpleName());
                }
                case 3 -> {
                    if (token instanceof Student) {
                        studentMenu.showStudentMenu(token);
                    } else
                        System.out.println("you are not authorised! your privilege is: " + token.getClass().getSimpleName());
                }
                case 4 -> condition = false;
                default -> System.out.println("Wrong option!");
            }
        }
    }
}
