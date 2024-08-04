package repository.Impl;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import repository.SelectUnitRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectUnitRepositoryImpl extends BaseEntityRepositoryImpl<SelectUnit> implements SelectUnitRepository {

    public SelectUnitRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<SelectUnit> getEntityClass() {
        return SelectUnit.class;
    }


    @Override
    public Map<Map<String, Integer>, Double> getLessonWithScore(Long studentId, Long termId) {
        /*
        select l.title,su.score from selectUnit su
        join student s on su.student_id = s.id
        join course c on su.course_id = c.id
        join term t on c.term_id = t.id
        join lesson l on c.lesson_id = l.id
        where s.id = StudentId
        and t.id =  termId;
         */
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

        Root<SelectUnit> selectUnitRoot = query.from(SelectUnit.class);

        //join student
        Join<SelectUnit, Student> student = selectUnitRoot.join("student");

        //join course
        Join<SelectUnit, Course> course = selectUnitRoot.join("course");

        //join term
        Join<Course, Term> term = course.join("term");

        //join lesson
        Join<Course, Lesson> lesson = course.join("lesson");
        //where clause
        query.where(criteriaBuilder.equal(student.get("id"), studentId),
                criteriaBuilder.equal(term.get("id"), termId));


        //select title,score
        query.multiselect(lesson.get("title"), lesson.get("unit"), selectUnitRoot.get("score"));
        List<Object[]> resultList = getEntityManager().createQuery(query).getResultList();


        Map<Map<String, Integer>, Double> resultMap = new HashMap<>();

        for (Object[] result : resultList) {
            String title = (String) result[0];
            Integer unit = (Integer) result[1];
            Double score = (Double) result[2];

            Map<String, Integer> internalMap = new HashMap<>();

            internalMap.put(title, unit);
            resultMap.put(internalMap, score);
        }
        return resultMap;
    }

    public void saveUnitSelection(Student student, Course course) {
        SelectUnit selectUnit = SelectUnit.builder().course(course).student(student).build();
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(selectUnit);
        entityManager.getTransaction().commit();
    }

    public boolean isPassLessonInPreviousTerms(Long studentId, Course course) {
        Long lessonId = course.getLesson().getId();
     /*
     select count(l.id) from selectunit u
     join student s on u.student_id = s.id
     join course c on u.course_id = c.id
     join lesson l on c.lesson_id = l.id
     join term t on c.term_id= t.id
     where u.score >10 and c.term_id<(select max(g.id) from term g) and s.id =studentId and l.id = lessonId

      */

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<SelectUnit> selectUnitRoot = query.from(SelectUnit.class);

        //join
        Join<SelectUnit, Student> student = selectUnitRoot.join("student");
        Join<SelectUnit, Course> course1 = selectUnitRoot.join("course");
        Join<Course, Lesson> lesson = course1.join("lesson");
        Join<Course, Term> term = course1.join("term");

        //Subquery
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Term> termRoot = subquery.from(Term.class);
        subquery.select(criteriaBuilder.max(termRoot.get("id")));

        //predicate
        Predicate score = criteriaBuilder.gt(selectUnitRoot.get("score"), 10);
        Predicate termId = criteriaBuilder.lt(term.get("id"), subquery);
        Predicate equalStudent = criteriaBuilder.equal(student.get("id"), studentId);
        Predicate equalLesson = criteriaBuilder.equal(lesson.get("id"), lessonId);

        //where
        query.select(criteriaBuilder.count(lesson.get("id")))
                .where(criteriaBuilder.and(score, termId, equalStudent, equalLesson));

        //count(l.id)
        Long countID = getEntityManager().createQuery(query).getSingleResult();
        return countID == 1;
    }

}


//----------------------------------------------------------------------------2
/*
@Override
public List<SelectUnit> getLessonWithScore(Long studentId, Long termId) {
        */
/*
        select l.title,su.score from selectUnit su
        join student s on su.student_id = s.id
        join course c on su.course_id = c.id
        join term t on c.term_id = t.id
        join lesson l on c.lesson_id = l.id
        where s.id = StudentId
        and t.id =  termId;
         *//*
 */
/*     TypedQuery<SelectUnit> query = getEntityManager().createQuery("""
                       from SelectUnit u
                                       join student s on u.student.id = s.id
                                       join course c on u.course.id = c.id
                                       join term t on c.term.id = t.id
                                       join lesson l on c.lesson.id = l.id
                                       where s.id = ?1
                                       and t.id =  ?2
                        """
                , SelectUnit.class);
        query.setParameter(1, studentId);
        query.setParameter(2, termId);
        List<SelectUnit> resultList = query.getResultList();
        return resultList;*//*
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<SelectUnit> query = criteriaBuilder.createQuery(SelectUnit.class);
    Root<SelectUnit> selectUnitRoot = query.from(SelectUnit.class);
    //join student
    Join<SelectUnit, Student> student = selectUnitRoot.join("student");
    //join course
    Join<SelectUnit, Course> course = selectUnitRoot.join("course");
    //join term
    Join<Course, Term> term = course.join("term");
    //join lesson
    Join<Course, Lesson> lesson = course.join("lesson");
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
            criteriaBuilder.equal(student.get("id"), studentId)
    );
    predicates.add(
            criteriaBuilder.equal(term.get("id"), termId)
    );
    if (!predicates.isEmpty()) {
        query.where(
                criteriaBuilder.and(
                        predicates.toArray(new Predicate[0])
                )
        );
    }
    return getEntityManager().createQuery(query).getResultList();
}
*/
//----------------------------------------------------3
   /*
@Override
public List<SelectUnit> getLessonWithScore(Long studentId, Long termId) {
/*
        select l.title,su.score from selectUnit su
        join student s on su.student_id = s.id
        join course c on su.course_id = c.id
        join term t on c.term_id = t.id
        join lesson l on c.lesson_id = l.id
        where s.id = StudentId
        and t.id =  termId;
         *//*
 */
/*     TypedQuery<SelectUnit> query = getEntityManager().createQuery("""
                       from SelectUnit u
                                       join student s on u.student.id = s.id
                                       join course c on u.course.id = c.id
                                       join term t on c.term.id = t.id
                                       join lesson l on c.lesson.id = l.id
                                       where s.id = ?1
                                       and t.id =  ?2
                        """
                , SelectUnit.class);
        query.setParameter(1, studentId);
        query.setParameter(2, termId);
        List<SelectUnit> resultList = query.getResultList();
        return resultList;
        }
 */