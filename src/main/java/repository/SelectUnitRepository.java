package repository;

import java.util.Map;

public interface SelectUnitRepository {
    Map<Map<String,Integer>, Double> getLessonWithScore(Long studentId, Long termId);
    //List<SelectUnit> getLessonWithScore(Long studentId, Long termId);
    // List<Object[]> getLessonWithScore(Long studentId, Long termId);

    Double getAvg(Long studentId, Long termId);
}