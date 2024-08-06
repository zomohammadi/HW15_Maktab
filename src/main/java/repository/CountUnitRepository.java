package repository;

public interface CountUnitRepository {
    Integer getCountOfSelectedUnit(Long studentId, Long termId);
}
