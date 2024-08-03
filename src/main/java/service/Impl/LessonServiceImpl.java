package service.Impl;

import entity.Lesson;
import repository.BaseEntityRepository;
import service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private BaseEntityRepository<Lesson> lessonRepository;

    public LessonServiceImpl(BaseEntityRepository<Lesson> lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public void update(Lesson lesson) {
        lessonRepository.update(lesson);
    }

    @Override
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

}
