package repository.Impl;

import entity.Course;
import entity.Term;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import repository.CourseRepository;

import java.util.List;

public class CourseRepositoryImpl extends BaseEntityRepositoryImpl<Course> implements CourseRepository {


    public CourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    public List<Course> getCourseInCurrentTerm() {
        /*
            select c from course c join term t on c.term_id=t.id where t.id= (select max(s.id) from term s)
               and c.capacity>0 //t.is_active =
         */
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> courseRoot = query.from(Course.class);
        //join with term
        Join<Course, Term> term = courseRoot.join("term");
        //create Subquery -> select max(s.id) from term s

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Term> termRoot = subquery.from(Term.class);
        //select max(id)
        subquery.select(criteriaBuilder.max(termRoot.get("id")));

        Predicate equal = criteriaBuilder.equal(term.get("id"), subquery);
        Predicate gt = criteriaBuilder.gt(courseRoot.get("capacity"), 0);
        //main query where claus
        query.select(courseRoot).where(criteriaBuilder.and(equal,gt));

        return getEntityManager().createQuery(query).getResultList();

    }


}
