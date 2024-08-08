
package repository.Impl;

import entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.TeacherRepository;

import java.util.List;

public class TeacherRepositoryImpl extends BaseEntityRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    public List<Long> getCourseTaughtByTeacher(Long teacherId, Long termId) {

        TypedQuery<Long> query = getEntityManager().createQuery("""
                select distinct u.course.id from SelectUnit u, Course c where u.course.id=c.id
                and c.term.id = ?1 and c.teacher.id = ?2
                """, Long.class);
        query.setParameter(1, termId);
        query.setParameter(2, teacherId);

        return query.getResultList();
    }

}

 /* CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();

        // Root for Course
        Root<Course> courseRoot = query.from(Course.class);

        // Subquery for calculating sum of units
        CriteriaQuery queryInner = criteriaBuilder.createQuery();
//        Subquery<Long> subquery = query.subquery(Long.class);
        Root<SelectUnit> selectUnitSubqueryRoot = queryInner.from(SelectUnit.class);
        Join<SelectUnit, Course> courseSubqueryRoot = selectUnitSubqueryRoot.join("course");
        Join<Course, Lesson> lessonJoin = courseSubqueryRoot.join("lesson");
        Join<Course, Teacher> teacherJoin = courseSubqueryRoot.join("teacher");

        queryInner.select(criteriaBuilder.sum(lessonJoin.get("unit")))
                .where(
                        criteriaBuilder.equal(courseSubqueryRoot.get("term").get("id"), termId),
                        criteriaBuilder.equal(teacherJoin.get("id"), teacherId)
                );
                courseRoot.select(queryInner);

        return getEntityManager().createQuery(query).getSingleResult()
  */