package service.Impl;

import entity.Employee;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final BaseEntityRepository<Employee> employeeRepository;
    private final EmployeeRepository employeeRepository1;

    public EmployeeServiceImpl(BaseEntityRepository<Employee> employeeRepository, EmployeeRepository employeeRepository1) {
        this.employeeRepository = employeeRepository;
        this.employeeRepository1 = employeeRepository1;
    }


    @Override
    public void save(Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            System.out.println("not save => " + e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            employeeRepository.update(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Employee employee = findById(id);
            employeeRepository.delete(employee);
        } catch (Exception e) {
            System.out.println("not Deleted!  " + e.getMessage());
        }
    }

    @Override
    public Employee findById(Long id) {
        try {
            return employeeRepository.findById(id);
        } catch (Exception e) {
            System.out.println("not save => " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            System.out.println("not save => " + e.getMessage());
            return null;
        }
    }

    @Override
    public Employee findByUserNameAndPassword(String userName, String password) {
        try {
            return employeeRepository.findByUserNameAndPassword(userName, password);
        } catch (NoResultException e) {
            System.out.println("No result found");
            return null;
        }
    }

    @Override
    public Employee findByCode(String code) {
        try {
            return employeeRepository1.findByCode(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
