package service.Impl;

import entity.Lesson;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private final BaseEntityRepository<Lesson> lessonRepository;

    public LessonServiceImpl(BaseEntityRepository<Lesson> lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void save(Lesson lesson) {
        try {
            lessonRepository.save(lesson);
        } catch (
                NoResultException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Lesson lesson) {
        try {
            lessonRepository.update(lesson);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Lesson lesson = findById(id);
            lessonRepository.delete(lesson);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Lesson findById(Long id) {
        try {
            return lessonRepository.findById(id);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Lesson> findAll() {
        try {
            return lessonRepository.findAll();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
