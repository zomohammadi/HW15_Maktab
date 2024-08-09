package repository;

import entity.Teacher;

import java.util.List;

public interface TeacherRepository {
    List<Long> getCourseTaughtByTeacher(Long teacherId, Long termId);
    List<Long> showStudentListOfTeacherInTerm(Long teacherId, Long termId);
    Teacher findByCode(String code);
    Long getSelectUnitId(Long teacherId, Long termId, Long studentId,long lessonId);
}
