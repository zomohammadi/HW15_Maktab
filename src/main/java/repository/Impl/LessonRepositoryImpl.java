
package repository.Impl;

import entity.Lesson;
import jakarta.persistence.EntityManager;
import repository.LessonRepository;

public class LessonRepositoryImpl extends BaseEntityRepositoryImpl<Lesson> implements LessonRepository {
    public LessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lesson> getEntityClass() {
        return Lesson.class;
    }

}
