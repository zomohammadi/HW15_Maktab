package service;

import entity.Course;
import entity.Student;

import java.util.Map;

public interface SelectUnitService {
    Map<Map<String, Integer>, Double> getLessonWithScore(Long studentId, Long termId);
    // List<SelectUnit> getLessonWithScore(Long studentId, Long termId);

    Double getAvg(Long studentId, Long termId);

    void saveUnitSelection(Student student, Course course);

    boolean isPassLessonInPreviousTerms(Long studentId, Course course);

    Integer getMaxSelectUnit(Long studentID, Long termId);

    boolean isLessenSelectedInCurrentSelectUnit(Course course, Map<Map<String, Integer>, Double> courseInCurrentTerm);

}