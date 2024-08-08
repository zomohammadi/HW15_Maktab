package repository;

import java.util.List;

public interface TeacherRepository {
    List<Long> getCourseTaughtByTeacher(Long teacherId, Long termId);
}
