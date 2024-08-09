package repository;

import entity.Course;
import entity.Student;

import java.util.Map;

public interface SelectUnitRepository {
    Map<Map<String,Integer>, Double> getLessonWithScore(Long studentId, Long termId);
    //List<SelectUnit> getLessonWithScore(Long studentId, Long termId);
    // List<Object[]> getLessonWithScore(Long studentId, Long termId);

   // Double getAvg(Long studentId, Long termId);
   void saveUnitSelection(Student student, Course course);
    boolean isPassLessonInPreviousTerms(Long studentId, Course course);

}