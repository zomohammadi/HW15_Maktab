package repository;

import entity.BaseEntity;

import java.util.List;

public interface BaseEntityRepository<T extends BaseEntity> {
    void save(T t);

    void update(T t);

    void delete(T t);

    List<T> findAll();

    T findById(Long id);

    T findByUserNameAndPassword(String userName, String password);
}
