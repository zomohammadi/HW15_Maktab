package service.Impl;

import entity.Course;
import entity.Student;
import repository.SelectUnitRepository;
import service.SelectUnitService;
import util.ApplicationContext;

import java.util.Map;

public class SelectUnitServiceImpl implements SelectUnitService {
    private SelectUnitRepository selectUnitRepository;

    public SelectUnitServiceImpl(SelectUnitRepository selectUnitRepository) {
        this.selectUnitRepository = selectUnitRepository;
    }

    @Override
    public Map<Map<String, Integer>, Double> getLessonWithScore(Long studentId, Long termId) {
        return selectUnitRepository.getLessonWithScore(studentId, termId);
    }

    @Override
    public Double getAvg(Long studentId, Long termId) {
        Map<Map<String, Integer>, Double> lessonWithScore = getLessonWithScore(studentId, termId);

        double sumScore = 0;
        double sumUnit = 0;

        for (Map.Entry<Map<String, Integer>, Double> entry : lessonWithScore.entrySet()) {
            Map<String, Integer> key = entry.getKey();
            Double score = 0.0;
            for (Map.Entry<String, Integer> internalEntry : key.entrySet()) {
                sumUnit += internalEntry.getValue();
                score = entry.getValue() * internalEntry.getValue();
            }
            sumScore += score;
        }
        return sumScore / sumUnit;
    }

    public int getMaxSelectUnit(Long studentID, Long termId) {
        Double avg = null;

        int maxSelectUnit = 0;
        if (termId > 1) {
            avg = getAvg(studentID, termId - 1);
        }
        if (avg >= 18.0) {
            maxSelectUnit = 24;
        } else if (avg < 18 || avg == null) {
            maxSelectUnit = 20;
        }
        return maxSelectUnit;
    }

    @Override
    public void saveUnitSelection(Student student, Course course) {
        int maxSelectUnit = getMaxSelectUnit(student.getId(), course.getTerm().getId());
        if (isPassLessonInPreviousTerms(student.getId(), course)) {
            System.out.println("can not select  the course! because you pass it in previous terms");
            return;
        } else {
            Map<Map<String, Integer>, Double> courseInCurrentTerm = getLessonWithScore(student.getId(), course.getTerm().getId());

            if (isLessenSelectedInCurrentSelectUnit(course, courseInCurrentTerm)) return;
        }

        selectUnitRepository.saveUnitSelection(student, course);
        course.setCapacity(course.getCapacity() - 1);
        ApplicationContext.getInstance().getCourseService().update(course);


        //return maxSelectUnit - course.getLesson().getUnit();
    }

    private static boolean isLessenSelectedInCurrentSelectUnit(Course course, Map<Map<String, Integer>, Double> courseInCurrentTerm) {
        for (Map.Entry<Map<String, Integer>, Double> entry : courseInCurrentTerm.entrySet()) {
            for (Map.Entry<String, Integer> integerMap : entry.getKey().entrySet()) {
                String title = integerMap.getKey();
                Integer unit = integerMap.getValue();
                if (title.equals(course.getLesson().getTitle()) && unit.equals(course.getLesson().getUnit())) {
                    System.out.println("can not select  the course! because you select the lessen in previous Selections");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isPassLessonInPreviousTerms(Long studentId, Course course) {
        return selectUnitRepository.isPassLessonInPreviousTerms(studentId, course);
    }
}