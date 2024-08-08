
package repository.Impl;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
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

    @Override
    public List<Object[]> getQueryResult(Long teacherId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        // Root entities
        Root<SelectUnit> selectUnit = cq.from(SelectUnit.class);
        Join<SelectUnit,Course > course = selectUnit.join("course");
        Join<Course, Teacher> teacher = course.join("teacher");
        Join<Course, Term> term = course.join("term");
        Join<SelectUnit, Student> student = selectUnit.join("student");

        // Subquery for the maximum term ID
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Term> subTerm = subquery.from(Term.class);
        subquery.select(cb.max(subTerm.get("id")));

        // Predicates
        Predicate teacherPredicate = cb.equal(teacher.get("id"), teacherId);
        Predicate termPredicate = cb.equal(term.get("id"), subquery);

        // Select clause
        cq.multiselect(student.get("id"), course.get("id"), selectUnit.get("id"))
                .where(cb.and(teacherPredicate, termPredicate));

        // Execute query
        return getEntityManager().createQuery(cq).getResultList();
    }
    @Override
    public Long getSumUnitsForTermAndTeacher(Long termId, Long teacherId) {
/*

        select sum(tbl.unit)
from course g,
     (select distinct c.id c_id, l.unit, t.id t_id
      from selectunit u
               join course c on c.id = u.course_id
               join lesson l on c.lesson_id = l.id
               join teacher t on c.teacher_id = t.id
      where c.term_id = 1
        and t.id = 1) tbl
where tbl.c_id = g.id
         */

        String subquery = "SELECT DISTINCT c.id, l.unit, t.id " +
                          "FROM SelectUnit u " +
                          "JOIN u.course c " +
                          "JOIN c.lesson l " +
                          "JOIN c.teacher t " +
                          "WHERE c.term.id = :termId " +
                          "AND t.id = :teacherId";

        List<Object[]> results = getEntityManager().createQuery(subquery)
                .setParameter("termId", 1)
                .setParameter("teacherId", 1)
                .getResultList();

// Step 2: Sum the units
        Long totalUnits = 0L;
        for (Object[] result : results) {
            Long unit = (Long) result[1];
            totalUnits += unit;
        }

        return totalUnits;
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