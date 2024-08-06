package service.Impl;

import entity.CountUnit;
import repository.BaseEntityRepository;
import repository.CountUnitRepository;
import service.CountUnitService;

public class CountUnitServiceImpl implements CountUnitService {
    private BaseEntityRepository<CountUnit> countUnitRepository;
    private CountUnitRepository countUnitRepository2;

    public CountUnitServiceImpl(BaseEntityRepository<CountUnit> countUnitRepository, CountUnitRepository countUnitRepository2) {
        this.countUnitRepository = countUnitRepository;
        this.countUnitRepository2 = countUnitRepository2;
    }


    @Override
    public void save(CountUnit countUnit) {
        countUnitRepository.save(countUnit);
    }

    @Override
    public void update(CountUnit countUnit) {
        countUnitRepository.update(countUnit);
    }

    @Override
    public Integer getCountOfSelectedUnit(Long studentId, Long termId) {
        try {
            return countUnitRepository2.getCountOfSelectedUnit(studentId, termId);
        } catch (Exception e) {
            System.out.println("not found " + e.getMessage());
            return null;
        }
    }
}
