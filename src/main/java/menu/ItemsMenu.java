package menu;

import entity.Employee;
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
        while (condition) {
            System.out.println("""
                        Items Menu:
                        1. Employee Operation
                        2. Teacher Operation
                        3. Student Operations
                        4. Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> {
                    if (token instanceof Employee) {
                        employeeMenu.showEmployeeMenu(token);
                    }else System.out.println("you are not authorised! your privilege is: "+ token.getClass().getSimpleName());
                }
                /*case 2 -> teacherMenu.showCardOperationMenu(token);
                case 3 -> studentMenu.showFinancialOperationsMenu(token);*/
                case 4 -> condition = false;
                default -> System.out.println("Wrong option!");
            }
        }
    }
}
