package repository;

import entity.CountUnit;

public interface CountUnitRepository {
    Integer getCountOfSelectedUnit(Long studentId, Long termId);
    CountUnit findCountUnit(Long studentId, Long termId);
}
