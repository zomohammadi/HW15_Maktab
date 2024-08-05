package service.Impl;

import entity.Employee;
import jakarta.persistence.NoResultException;
import repository.BaseEntityRepository;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private BaseEntityRepository<Employee> employeeRepository;

    public EmployeeServiceImpl(BaseEntityRepository<Employee> employeeRepository) {
        this.employeeRepository = employeeRepository;
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
        employeeRepository.update(employee);
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
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
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
}
