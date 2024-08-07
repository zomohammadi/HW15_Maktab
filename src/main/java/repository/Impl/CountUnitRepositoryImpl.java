package repository.Impl;

import entity.CountUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CountUnitRepositoryImpl extends BaseEntityRepositoryImpl<CountUnit> implements repository.CountUnitRepository {
    public CountUnitRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CountUnit> getEntityClass() {
        return CountUnit.class;
    }

    @Override
    public Integer getCountOfSelectedUnit(Long studentId, Long termId) {
        /*
          select countofselectedunit from CountUnit c where c.student_d = 1 and c.term_id = 1;
         */
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);

        Root<CountUnit> countUnitRoot = query.from(CountUnit.class);
        //select countofselectedunit
        query.select(countUnitRoot.get("countOfSelectedUnit"));

        //where clause
        query.where(
                criteriaBuilder.equal(countUnitRoot.get("student").get("id"), studentId),
                criteriaBuilder.equal(countUnitRoot.get("term").get("id"), termId)
        );
        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public CountUnit findCountUnit(Long studentId, Long termId) {
        /*
          select CountUnit from CountUnit c where c.student_d = 1 and c.term_id = 1;
         */
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CountUnit> query = criteriaBuilder.createQuery(CountUnit.class);

        Root<CountUnit> countUnitRoot = query.from(CountUnit.class);

        //where clause
        query.where(
                criteriaBuilder.equal(countUnitRoot.get("student").get("id"), studentId),
                criteriaBuilder.equal(countUnitRoot.get("term").get("id"), termId)
        );
        return getEntityManager().createQuery(query).getSingleResult();
    }
}
