package service.Impl;

import entity.CountUnit;
import repository.BaseEntityRepository;
import repository.CountUnitRepository;
import service.CountUnitService;

public class CountUnitServiceImpl implements CountUnitService {
    private final BaseEntityRepository<CountUnit> countUnitRepository;
    private final CountUnitRepository countUnitRepository2;

    public CountUnitServiceImpl(BaseEntityRepository<CountUnit> countUnitRepository, CountUnitRepository countUnitRepository2) {
        this.countUnitRepository = countUnitRepository;
        this.countUnitRepository2 = countUnitRepository2;
    }


    @Override
    public void save(CountUnit countUnit) {
        try {
            countUnitRepository.save(countUnit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(CountUnit countUnit) {
        try {
            countUnitRepository.update(countUnit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    @Override
    public CountUnit findCountUnit(Long studentId, Long termId) {
        try {
            return countUnitRepository2.findCountUnit(studentId, termId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
