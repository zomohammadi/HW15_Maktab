package service.Impl;

import entity.Teacher;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import repository.TeacherRepository;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final BaseEntityRepository<Teacher> teacherRepository;
    private final TeacherRepository teacherRepository2;

    public TeacherServiceImpl(BaseEntityRepository<Teacher> teacherRepository, TeacherRepository teacherRepository2) {
        this.teacherRepository = teacherRepository;
        this.teacherRepository2 = teacherRepository2;
    }

    @Override
    public void save(Teacher teacher) {
        try {
            teacherRepository.save(teacher);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Teacher teacher) {
        try {
            teacherRepository.update(teacher);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Teacher teacher = findById(id);
            teacherRepository.delete(teacher);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Teacher findById(Long id) {
        try {
            return teacherRepository.findById(id);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Teacher> findAll() {
        try {
            return teacherRepository.findAll();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Teacher findByUserNameAndPassword(String userName, String password) {
        try {

            return teacherRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found" + e.getMessage());
            return null;
        }
    }

    @Override
    public Long getSumUnitsForTermAndTeacher(Long termId, Long teacherId) {
        try {
            return teacherRepository2.getSumUnitsForTermAndTeacher(termId, teacherId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object[]> getQueryResult(Long teacherId) {
        return teacherRepository2.getQueryResult(teacherId);
    }
}
