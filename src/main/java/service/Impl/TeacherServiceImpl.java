package service.Impl;

import entity.Student;
import entity.Teacher;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import repository.TeacherRepository;
import service.TeacherService;
import util.ApplicationContext;

import java.util.ArrayList;
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
    public List<Long> getCourseTaughtByTeacher(Long teacherId, Long termId) {
        try {
            return teacherRepository2.getCourseTaughtByTeacher(teacherId, termId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Integer> getUnitsThatTaughtByTeacher(List<Long> course_id) {
        try {
            List<Integer> units = new ArrayList<>();
            for (Long id : course_id) {
                Integer unit = ApplicationContext.getInstance().getCourseService().findById(id).getLesson().getUnit();
                units.add(unit);
            }
            return units;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer getSumUnits(List<Integer> units) {
        try {
            Integer sum = 0;
            for (Integer unit : units) {
                sum += unit;
            }
            return sum;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Long> showStudentIdListOfTeacherInTerm(Long teacherId, Long termId) {
        try {
            return teacherRepository2.showStudentListOfTeacherInTerm(teacherId, termId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Student> showStudentListOfTeacherInTerm(List<Long> studentIds) {
        try {
            List<Student> students = new ArrayList<>();
            for (Long id : studentIds) {
                Student student = ApplicationContext.getInstance().getStudentService().findById(id);
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Teacher findByCode(String code) {
        try {
            return teacherRepository2.findByCode(code);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Long getSelectUnitId(Long teacherId, Long termId, Long studentId,long lessonId) {
        try {
            return teacherRepository2.getSelectUnitId(teacherId,termId,studentId,lessonId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
