package service.Impl;

import entity.Course;
import entity.SelectUnit;
import entity.Student;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import repository.SelectUnitRepository;
import service.SelectUnitService;
import util.ApplicationContext;

import java.util.Map;

public class SelectUnitServiceImpl implements SelectUnitService {
    private final SelectUnitRepository selectUnitRepository;
    private final BaseEntityRepository<SelectUnit> selectUnitRepositoryBase;

    public SelectUnitServiceImpl(SelectUnitRepository selectUnitRepository, BaseEntityRepository<SelectUnit> selectUnitRepositoryBase) {
        this.selectUnitRepository = selectUnitRepository;
        this.selectUnitRepositoryBase = selectUnitRepositoryBase;
    }

    @Override
    public Map<Map<String, Integer>, Double> getLessonWithScore(Long studentId, Long termId) {
        try {
            return selectUnitRepository.getLessonWithScore(studentId, termId);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Double getAvg(Long studentId, Long termId) {
        Map<Map<String, Integer>, Double> lessonWithScore = getLessonWithScore(studentId, termId);

        double sumScore = 0;
        double sumUnit = 0;
        if (lessonWithScore.size() == 0)
            return 0.0;
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

    @Override
    public Integer getMaxSelectUnit(Long studentID, Long termId) {
        Double avg;

        Integer maxSelectUnit = null;
        if (termId > 1) {
            avg = getAvg(studentID, termId - 1);
        } else {
            avg = 0.0;
        }
        if (avg >= 18.0) {
            maxSelectUnit = 24;
        } else if (avg < 18) {
            maxSelectUnit = 20;
        }
        return maxSelectUnit;
    }

    @Override
    public void saveUnitSelection(Student student, Course course) {
        try {
            selectUnitRepository.saveUnitSelection(student, course);
            course.setCapacity(course.getCapacity() - 1);
            ApplicationContext.getInstance().getCourseService().update(course);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }

/*
        return maxSelectUnit - course.getLesson().getUnit();*/
    }

    public boolean isLessenSelectedInCurrentSelectUnit(Course course, Map<Map<String, Integer>, Double> courseInCurrentTerm) {
        for (Map.Entry<Map<String, Integer>, Double> entry : courseInCurrentTerm.entrySet()) {
            for (Map.Entry<String, Integer> integerMap : entry.getKey().entrySet()) {
                String title = integerMap.getKey();
                Integer unit = integerMap.getValue();
                if (title.equals(course.getLesson().getTitle()) && unit.equals(course.getLesson().getUnit())) {
                    System.out.println("can not select  the course! because you select the lessen in previous Selections");
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean isPassLessonInPreviousTerms(Long studentId, Course course) {
        try {
            return selectUnitRepository.isPassLessonInPreviousTerms(studentId, course);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public void update(SelectUnit selectUnit) {
        try {
            selectUnitRepositoryBase.update(selectUnit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public SelectUnit findById(Long id) {
        try {
            return selectUnitRepositoryBase.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}