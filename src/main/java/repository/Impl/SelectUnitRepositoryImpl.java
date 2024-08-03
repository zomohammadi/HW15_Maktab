package repository.Impl;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
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