package menu;

import entity.Teacher;
import entity.User;
import enumration.TeacherType;
import service.SelectUnitService;
import service.TeacherService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {

    private final TeacherService teacherService;
    private final SelectUnitService selectUnitService;///////

    public TeacherMenu(TeacherService teacherService, SelectUnitService selectUnitService) {
        this.teacherService = teacherService;
        this.selectUnitService = selectUnitService;
    }

    public void showTeacherMenu(User token) {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            System.out.println("""
                        Teacher Operation Menu:
                        1. Show Information
                        2. Score Registration
                        3. Show Pay Slip
                        4. Exit
                    """);

            System.out.print("Option: ");
            String stringOption = input.nextLine();
            if (stringOption == null || stringOption.isEmpty()) {
                System.out.println("Input can not be null or empty");
                showTeacherMenu(token);
                break;
            }
            try {
                Integer option = Integer.parseInt(stringOption);


                switch (option) {
                    case 1 -> showInformation(token);
                   /* case 2 -> {

                    }*/
                    case 3 -> showPaySlip(token, input);
                    case 4 -> condition = false;
                    default -> System.out.println("Wrong option!");
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("Wrong option!");
                }
            }
        }
    }

    private void showPaySlip(User token, Scanner input) {

        System.out.print("Enter the TermId: ");
        Long termId = checkNumber(input);
        if (termId != null) {
            List<Long> courseIdList = teacherService.getCourseTaughtByTeacher(token.getId(), termId);
            if (courseIdList == null || courseIdList.size() == 0) {
                System.out.println("you are not Taught any Course ");
            } else {
                List<Integer> unitsThatTaughtByTeacher = teacherService.getUnitsThatTaughtByTeacher(courseIdList);
                Integer sumUnits = teacherService.getSumUnits(unitsThatTaughtByTeacher);
                Double salary = null;
                if (((Teacher) token).getTeacherType().equals(TeacherType.CONTRACTUAL)) {
                    salary = sumUnits * 1000000.0;
                } else {
                    salary = ((Teacher) token).getBaseSalary() + (sumUnits * 1000000.0);
                }
                showInformation(token);
                System.out.println("Count of Units that Teacher though: " + sumUnits);
                System.out.println("Salary is : " + NumberFormat.getInstance().format(salary));
            }
        } /*else System.out.println("You have not taught a course this term! ");*/
    }

    private void showInformation(User token) {
        System.out.println(token);
    }

    private Long checkNumber(Scanner input) {
        String id = input.nextLine();
        if (id == null || id.isEmpty()) {
            System.out.println("Input can not be null or empty");
            return null;
        }
        char[] chars = id.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                System.out.println("Input must contain only digit between (0-9)");
                return null;
            }
        }
        return Long.valueOf(id);
    }
}
