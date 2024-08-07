package service;

import entity.CountUnit;

public interface CountUnitService {
    void save(CountUnit countUnit);
    void update(CountUnit countUnit);
    Integer getCountOfSelectedUnit(Long studentId, Long termId);
    CountUnit findCountUnit(Long studentId, Long termId);
}
