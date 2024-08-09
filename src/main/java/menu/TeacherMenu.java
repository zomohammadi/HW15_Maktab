package menu;

import entity.SelectUnit;
import entity.Student;
import entity.Teacher;
import entity.User;
import enumration.TeacherType;
import service.SelectUnitService;
import service.StudentService;
import service.TeacherService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {

    private final TeacherService teacherService;
    private final SelectUnitService selectUnitService;///////
    private final StudentService studentService;

    public TeacherMenu(TeacherService teacherService, SelectUnitService selectUnitService, StudentService studentService) {
        this.teacherService = teacherService;
        this.selectUnitService = selectUnitService;
        this.studentService = studentService;
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

                sw:
                switch (option) {
                    case 1 -> showInformation(token);
                    case 2 -> scoreRegistration(token,input);
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

    private void scoreRegistration(User token, Scanner input) {
        System.out.print("Enter the TermId: ");
        Long termId = checkNumber(input);
        if (termId != null) {
            List<Long> studentId = teacherService.showStudentIdListOfTeacherInTerm(token.getId(), termId);
            if (studentId.size() == 0 || studentId == null) {
                System.out.println("not fond student! ");
            } else {
                System.out.println("Student List: ");
                teacherService.showStudentListOfTeacherInTerm(studentId).forEach(System.out::println);
                System.out.print("Enter the Student Code that you are register Score: ");
                String studentCode = input.nextLine();
                if (!fillInputNumbers(studentCode, 5)) {
                    return;
                }
                if (studentCode != null) {
                    Student student = studentService.findByCode(studentCode);
                    if (student != null) {
                        System.out.print("Enter the lessonId: ");
                        Long lessonId = checkNumber(input);
                        if (lessonId != null) {
                            Long selectUnitId = teacherService.getSelectUnitId(token.getId(), termId, student.getId(), lessonId);
                            SelectUnit selectUnit = selectUnitService.findById(selectUnitId);
                            if (selectUnit != null) {
                                System.out.println("enter the Score");
                                String score = input.nextLine();
                                char[] chars = score.toCharArray();
                                for (char c : chars) {
                                    if (!Character.isDigit(c)) {
                                        System.out.println("input must contain only numbers between (0-9)");
                                        break;
                                    }
                                }
                                if (Double.parseDouble(score) > 20 || Double.parseDouble(score) < 0) {
                                    System.out.println("Enter the correct Score (between 0 and 20)");
                                    return;
                                } else {
                                    selectUnit.setScore(Double.valueOf(score));
                                    selectUnitService.update(selectUnit);
                                    System.out.println("Done!");
                                }
                            } else System.out.println("no course with student found");
                        }
                    } else {
                        System.out.println("no Student found! ");
                    }
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

    private boolean checkedNullInput(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Input can not be null or empty");
            return true;
        }
        return false;
    }
}
