package service;

import entity.Lesson;

import java.util.List;

public interface LessonService {
    void save(Lesson lesson);
    void update(Lesson lesson);
    void delete(Long id);
    Lesson findById(Long id);
    List<Lesson> findAll();

}
