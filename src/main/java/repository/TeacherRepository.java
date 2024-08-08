package repository;

import java.util.List;

public interface TeacherRepository {
    List<Object[]> getQueryResult(Long teacherId);
    Long getSumUnitsForTermAndTeacher(Long termId, Long teacherId);
}
