package service.Impl;

import repository.SelectUnitRepository;
import service.SelectUnitService;

import java.util.Map;

public class SelectUnitServiceImpl implements SelectUnitService {
    private SelectUnitRepository selectUnitRepository;

    public SelectUnitServiceImpl(SelectUnitRepository selectUnitRepository) {
        this.selectUnitRepository = selectUnitRepository;
    }

    @Override
    public  Map<Map<String,Integer>, Double> getLessonWithScore(Long studentId, Long termId) {
        return selectUnitRepository.getLessonWithScore(studentId,termId);
    }

    @Override
    public Double getAvg(Long studentId, Long termId) {
        return selectUnitRepository.getAvg(studentId,termId);
    }
}