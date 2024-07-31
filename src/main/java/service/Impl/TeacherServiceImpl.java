package service.Impl;

import entity.Teacher;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private BaseEntityRepository<Teacher> teacherRepository;

    public TeacherServiceImpl(BaseEntityRepository<Teacher> teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.update(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findByUserNameAndPassword(String userName, String password) {
        try {

            return teacherRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found");
            return null;
        }
    }
}
