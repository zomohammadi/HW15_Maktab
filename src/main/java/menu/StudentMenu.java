package menu;

import entity.*;
import service.CourseService;
import service.SelectUnitService;
import service.StudentService;
import util.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentMenu {

    private final StudentService studentService;
    private final SelectUnitService selectUnitService;
    private final CourseService courseService;

    public StudentMenu(StudentService studentService, SelectUnitService selectUnitService, CourseService courseService) {
        this.studentService = studentService;
        this.selectUnitService = selectUnitService;
        this.courseService = courseService;
    }

    public void showStudentMenu(User token) {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        studentLable:
        while (condition) {
            System.out.println("""
                        Student Operation Menu:
                        1. Show Information
                        2. Show Lesson List in Current Term
                        3. Select Unit
                        4. Show Student Lessen with Score in each term
                        5. Exit
                    """);
            System.out.print("Option: ");
            int option = input.nextInt();
            input.nextLine();
            sw:
            switch (option) {
                case 1 -> showInformation(token);
                case 2 -> showLessonsInCurrentTerm(input);
                case 3 -> selectUnit(token, input);
                case 4 -> showLessonsWithScoreInEachTerm(token, input);
                case 5 -> condition = false;
                default -> System.out.println("Wrong option!");
            }
        }
    }

    private void selectUnit(User token, Scanner input) {
        List<Course> courseInCurrentTerm = ApplicationContext.getInstance().getCourseService().getCourseInCurrentTerm();
        courseInCurrentTerm.forEach(System.out::println);
        Long termId = courseInCurrentTerm.get(0).getTerm().getId();
        Long studentId = token.getId();
        Integer maxSelectUnit = ApplicationContext.getInstance().getSelectUnitService()
                .getMaxSelectUnit(studentId, termId);
        // selectUnit(token,input,courseInCurrentTerm);

        System.out.print("Enter the Course Id: ");

        Long courseId = checkNumber(input);
        if (courseId != null) {
            Course course = null;
            for (Course c : courseInCurrentTerm) {
                if (c.getId().equals(courseId)) {
                    course = c;
                    break;
                }
            }
            if (course == null) {
                System.out.println("plz the course id in this list ");
                return;
            }
            Integer countOfSelectedUnit = ApplicationContext.getInstance().getCountUnitService().getCountOfSelectedUnit(studentId, termId);
            if (countOfSelectedUnit==null){
                countOfSelectedUnit=0;
            }
            if (maxSelectUnit - countOfSelectedUnit == 0) {
                System.out.println("your maximum selected the unit is full");
                return;
            } else if (maxSelectUnit - (countOfSelectedUnit + course.getLesson().getUnit()) < 0) {
                System.out.println("""
                        you are not allow to choose the course!
                        By choosing this course, the number of selected units becomes more than the allowed limit
                        """);
                return;
            }
            /*Integer maxSelectUnit = ApplicationContext.getInstance().getSelectUnitService()
                    .getMaxSelectUnit(studentId, termId);*/

            if (ApplicationContext.getInstance().getSelectUnitService()
                    .isPassLessonInPreviousTerms(studentId, course)) {
                System.out.println("can not select  the course! because you pass it in previous terms");
                return;
            } else {
                Map<Map<String, Integer>, Double> courseIsSelectInCurrentTerm = ApplicationContext.getInstance().getSelectUnitService()
                        .getLessonWithScore(studentId, termId);

                if (ApplicationContext.getInstance().getSelectUnitService()
                        .isLessenSelectedInCurrentSelectUnit(course, courseIsSelectInCurrentTerm))
                    return;
            }
            ApplicationContext.getInstance().getSelectUnitService().saveUnitSelection((Student) token, course);
            if (countOfSelectedUnit ==0){
                countOfSelectedUnit += course.getLesson().getUnit();
                Term term = new Term();
                term.setId(termId);
                CountUnit countUnit = new CountUnit((Student) token, term, countOfSelectedUnit);
                ApplicationContext.getInstance().getCountUnitService().save(countUnit);
                System.out.println("Select Unit Done! ");
            }else {
                CountUnit countUnit = ApplicationContext.getInstance().getCountUnitService().findCountUnit(studentId, termId);
                countOfSelectedUnit += course.getLesson().getUnit();
                countUnit.setCountOfSelectedUnit(countOfSelectedUnit);
                ApplicationContext.getInstance().getCountUnitService().update(countUnit);
                System.out.println("Select Unit Done! ");
            }

        }
    }
/*
    private static CountUnit initializeCountUnit(Integer countOfSelectedUnit, Course course, Long termId, Student token) {
        countOfSelectedUnit += course.getLesson().getUnit();
        Term term = new Term();
        term.setId(termId);
        CountUnit countUnit = new CountUnit(token, term, countOfSelectedUnit);
        return countUnit;
    }*/

    private void showLessonsWithScoreInEachTerm(User token, Scanner input) {
        System.out.print("Enter the term Id: ");
        Long termId = checkNumber(input);
        if (termId != null) {
            Map<Map<String, Integer>, Double> lessonWithScore = ApplicationContext.getInstance().getSelectUnitService().getLessonWithScore(token.getId(), termId);
            if (lessonWithScore.size() != 0) {
                for (Map.Entry<Map<String, Integer>, Double> entry : lessonWithScore.entrySet()) {
                    Map<String, Integer> key = entry.getKey();
                    for (Map.Entry<String, Integer> integerEntry : key.entrySet()) {
                        System.out.print(integerEntry.getKey() + "(" + integerEntry.getValue() + " unit)");
                    }
                    System.out.println(" -> Score= " + entry.getValue());
                }
            } else System.out.println("not pass lessen int this term " + termId);
        }
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

    private void showLessonsInCurrentTerm(Scanner input) {
        List<Course> courseInCurrentTerm = ApplicationContext.getInstance().
                getCourseService().getCourseInCurrentTerm();
        courseInCurrentTerm.forEach(System.out::println);
    }

    private void showInformation(User token) {
        System.out.println(token);
    }
}
